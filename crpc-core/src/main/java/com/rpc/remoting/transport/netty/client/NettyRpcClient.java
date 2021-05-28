package com.rpc.remoting.transport.netty.client;

import com.rpc.config.ProtocolConfig;
import com.rpc.config.cache.ConfigCache;
import com.rpc.enums.CompressTypeEnum;
import com.rpc.enums.SerializationTypeEnum;
import com.rpc.extension.ExtensionLoader;
import com.rpc.factory.SingletonFactory;
import com.rpc.registry.ServiceDiscovery;
import com.rpc.remoting.constants.RpcConstants;
import com.rpc.remoting.dto.RpcMessage;
import com.rpc.remoting.dto.RpcRequest;
import com.rpc.remoting.dto.RpcResponse;
import com.rpc.remoting.transport.RpcRequestTransport;
import com.rpc.remoting.transport.netty.codec.RpcMessageDecoder;
import com.rpc.remoting.transport.netty.codec.RpcMessageEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import java.net.InetSocketAddress;
import io.netty.channel.socket.SocketChannel;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * rpc客户端的Netty实现
 * @author cao wei
 */
@Slf4j
public final class NettyRpcClient implements RpcRequestTransport {
    private final ServiceDiscovery serviceDiscovery;
    private final UnprocessedRequests unprocessedRequests;
    private final ChannelProvider channelProvider;
    private final Bootstrap bootstrap;
    private final EventLoopGroup eventLoopGroup;
    private final ProtocolConfig protocolConfig;

    public NettyRpcClient() {
        // initialize resources such as EventLoopGroup, Bootstrap
        eventLoopGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                //  The timeout period of the connection.
                //  If this time is exceeded or the connection cannot be established, the connection fails.
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ChannelPipeline p = ch.pipeline();
                        // 心跳机制
                        p.addLast(new IdleStateHandler(0, 5, 0, TimeUnit.SECONDS));
                        // 编码解码器
                        p.addLast(new RpcMessageEncoder());
                        p.addLast(new RpcMessageDecoder());
                        // 客户端Handler的自定义，用于处理服务端响应的数据
                        p.addLast(new NettyRpcClientHandler());
                    }
                });
        this.serviceDiscovery = ExtensionLoader.getExtensionLoader(ServiceDiscovery.class).getExtension("zk");
        this.unprocessedRequests = SingletonFactory.getInstance(UnprocessedRequests.class);
        this.channelProvider = SingletonFactory.getInstance(ChannelProvider.class);
        this.protocolConfig = ConfigCache.getConfig(ProtocolConfig.class);
    }

    /**
     * connect server and get the channel ,so that you can send rpc message to server
     *
     * @param inetSocketAddress server address
     * @return the channel
     */
    @SneakyThrows
    public Channel doConnect(InetSocketAddress inetSocketAddress) {
        CompletableFuture<Channel> completableFuture = new CompletableFuture<>();
        bootstrap.connect(inetSocketAddress).addListener((ChannelFutureListener) future -> {
            if (future.isSuccess()) {
                log.info("The client has connected [{}] successful!", inetSocketAddress.toString());
                completableFuture.complete(future.channel());
            } else {
                throw new IllegalStateException();
            }
        });
        return completableFuture.get();
    }

    @Override
    public Object sendRpcRequest(RpcRequest rpcRequest) {
        // CompletableFuture实现异步响应
        CompletableFuture<RpcResponse<Object>> resultFuture = new CompletableFuture<>();
        // 获取调用的服务名
        String rpcServiceName = rpcRequest.toRpcProperties().toRpcServiceName();
        // 服务发现拿到地址信息
        InetSocketAddress inetSocketAddress = serviceDiscovery.lookupService(rpcServiceName);
        Channel channel = getChannel(inetSocketAddress);
        if (channel.isActive()) {
            // 将请求暂时存储在UnprocessedRequests中，成功后再取出响应数据
            unprocessedRequests.put(rpcRequest.getRequestId(), resultFuture);
            RpcMessage rpcMessage = RpcMessage.builder().data(rpcRequest)
                    .codec(protocolConfig.getSerializationType().getCode())
                    .compress(CompressTypeEnum.GZIP.getCode())
                    .messageType(RpcConstants.REQUEST_TYPE).build();
            channel.writeAndFlush(rpcMessage).addListener((ChannelFutureListener) future -> {
                // 利用JDK提供的future实现异步响应
                if (future.isSuccess()) {
                    log.info("client send message: [{}]", rpcMessage);
                } else {
                    future.channel().close();
                    resultFuture.completeExceptionally(future.cause());
                    log.error("Send failed:", future.cause());
                }
            });
        } else {
            throw new IllegalStateException();
        }

        return resultFuture;
    }

    public Channel getChannel(InetSocketAddress inetSocketAddress) {
        Channel channel = channelProvider.get(inetSocketAddress);
        if (channel == null) {
            channel = doConnect(inetSocketAddress);
            channelProvider.set(inetSocketAddress, channel);
        }
        return channel;
    }

    public void close() {
        eventLoopGroup.shutdownGracefully();
    }
}
