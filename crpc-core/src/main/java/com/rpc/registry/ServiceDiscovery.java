package com.rpc.registry;

import com.rpc.entity.RpcServiceProperties;
import com.rpc.extension.SPI;
import java.net.InetSocketAddress;

/**
 * 服务发现
 * @author cao wei
 */
@SPI
public interface ServiceDiscovery {
    /**
     * lookup service by rpcServiceName
     *
     * @param rpcServiceName rpc service name
     * @return service address
     */
    InetSocketAddress lookupService(String rpcServiceName);

    /**
     * 订阅某个服务
     * @param properties 服务信息
     */
    void subscribe(RpcServiceProperties properties);

    /**
     * 取消订阅服务
     * @param properties 服务信息
     */
    void unsubscribe(RpcServiceProperties properties);
}
