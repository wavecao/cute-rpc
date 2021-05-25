package com.rpc.loadbalance.loadbalancer;

import com.rpc.loadbalance.AbstractLoadBalance;
import java.util.List;
import java.util.Random;

/**
 * 随机负载均衡
 * @author cao wei
 */
public class RandomLoadBalance extends AbstractLoadBalance {
    @Override
    protected String doSelect(List<String> serviceAddresses, String rpcServiceName) {
        Random random = new Random();
        return serviceAddresses.get(random.nextInt(serviceAddresses.size()));
    }
}
