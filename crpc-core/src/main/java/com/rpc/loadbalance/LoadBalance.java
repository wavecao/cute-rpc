package com.rpc.loadbalance;

import com.rpc.extension.SPI;
import java.util.List;

/**
 * 负载均衡最高的接口
 * @author cao wei
 */
@SPI
public interface LoadBalance {
    /**
     * Choose one from the list of existing service addresses list
     *
     * @param serviceAddresses Service address list
     * @return target service address
     */
    String selectServiceAddress(List<String> serviceAddresses, String rpcServiceName);
}
