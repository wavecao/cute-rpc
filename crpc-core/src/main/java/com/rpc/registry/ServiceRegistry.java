package com.rpc.registry;

import com.rpc.entity.RpcServiceProperties;
import com.rpc.extension.SPI;
import java.net.InetSocketAddress;

/**
 * 服务注册
 * @author cao wei
 */
@SPI
public interface ServiceRegistry {
    /**
     * register service
     *
     * @param rpcServiceName    rpc service name
     * @param inetSocketAddress service address
     */
    void registerService(String rpcServiceName, InetSocketAddress inetSocketAddress);

    /**
     * 注销服务
     * @param rpcServiceName
     * @param inetSocketAddress
     */
    void unregister(String rpcServiceName, InetSocketAddress inetSocketAddress);

    void offline(RpcServiceProperties properties);

    void shutdown(RpcServiceProperties properties);

    boolean isShutDown(RpcServiceProperties properties);
}
