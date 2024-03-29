package com.rpc.remoting.transport.netty.server;

import com.rpc.config.CustomShutdownHook;
import com.rpc.config.ServerConfig;
import com.rpc.config.cache.ConfigCache;
import com.rpc.entity.RpcServiceProperties;
import com.rpc.factory.SingletonFactory;
import com.rpc.provider.ServiceProvider;
import com.rpc.provider.ServiceProviderImpl;
import com.rpc.registry.zk.util.CuratorUtils;
import com.rpc.remoting.transport.netty.codec.RpcMessageDecoder;
import com.rpc.remoting.transport.netty.codec.RpcMessageEncoder;
import com.rpc.utils.NetUtil;
import com.rpc.utils.RuntimeUtil;
import com.rpc.utils.threadpool.ThreadPoolFactoryUtils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 基于Netty实现的服务器
 * @author cao wei
 */
@Slf4j
@Component
public class NettyRpcServer {

    protected static final Logger logger = LoggerFactory.getLogger(NettyRpcServer.class);

    private final ServerConfig serverConfig = ConfigCache.getConfig(ServerConfig.class);

    private final ServiceProvider serviceProvider = SingletonFactory.getInstance(
        ServiceProviderImpl.class);

    public void registerService(Object service, RpcServiceProperties rpcServiceProperties) {
        serviceProvider.publishService(service, rpcServiceProperties);
    }

    private Thread thread;

    @SneakyThrows
    public void start() {

        thread = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                CustomShutdownHook.getCustomShutdownHook().clearAll();
                String host = NetUtil.getLocalAddress();
                int port = serverConfig.getRpcServerPort();
                EventLoopGroup bossGroup = new NioEventLoopGroup(1);
                EventLoopGroup workerGroup = new NioEventLoopGroup();
                DefaultEventExecutorGroup serviceHandlerGroup = new DefaultEventExecutorGroup(
                    RuntimeUtil.cpus() * 2,
                    ThreadPoolFactoryUtils.createThreadFactory("service-handler-group", false)
                );
                try {
                    ServerBootstrap b = new ServerBootstrap();
                    b.group(bossGroup, workerGroup)
                        .channel(NioServerSocketChannel.class)
                        // TCP默认开启了 Nagle 算法，该算法的作用是尽可能的发送大数据快，减少网络传输。TCP_NODELAY 参数的作用就是控制是否启用 Nagle 算法。
                        .childOption(ChannelOption.TCP_NODELAY, true)
                        // 是否开启 TCP 底层心跳机制
                        .childOption(ChannelOption.SO_KEEPALIVE, true)
                        //表示系统用于临时存放已完成三次握手的请求的队列的最大长度,如果连接建立频繁，服务器处理创建新连接较慢，可以适当调大这个参数
                        .option(ChannelOption.SO_BACKLOG, 128)
                        .handler(new LoggingHandler(LogLevel.INFO))
                        // 当客户端第一次进行请求的时候才会进行初始化
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) {
                                // 30 秒之内没有收到客户端请求的话就关闭连接
                                ChannelPipeline p = ch.pipeline();
                                p.addLast(new IdleStateHandler(30, 0, 0, TimeUnit.SECONDS));
                                // 编码器和解码器
                                p.addLast(new RpcMessageEncoder());
                                p.addLast(new RpcMessageDecoder());
                                // 自定义服务器的ChannelHandler以处理客户端发送的数据。
                                p.addLast(serviceHandlerGroup, new NettyRpcServerHandler());
                            }
                        });

                    // 绑定端口，同步等待绑定成功
                    ChannelFuture f = b.bind(host, port).sync();
                    // 等待服务端监听端口关闭
                    f.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                    log.error("occur exception when start server:", e);
                } finally {
                    log.error("shutdown bossGroup and workerGroup");
                    bossGroup.shutdownGracefully();
                    workerGroup.shutdownGracefully();
                    serviceHandlerGroup.shutdownGracefully();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public void stop() {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(NetUtil.getLocalAddress(),
            ConfigCache.getConfig(ServerConfig.class).getRpcServerPort());
        CuratorUtils.clearRegistry(CuratorUtils.getZkClient(), inetSocketAddress);
        if (thread != null && thread.isAlive()) {
            thread.interrupt();
        }
        logger.info(">>>>>>>>>>> cute-rpc remoting server destroy succeed.");
    }
}
