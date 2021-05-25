package com.rpc.registry.zk;


import com.rpc.config.ProtocolConfig;
import com.rpc.config.cache.ConfigCache;
import com.rpc.entity.RpcServiceProperties;
import com.rpc.enums.RpcErrorMessageEnum;
import com.rpc.exception.RpcException;
import com.rpc.extension.ExtensionLoader;
import com.rpc.loadbalance.LoadBalance;
import com.rpc.registry.ServiceDiscovery;
import com.rpc.registry.zk.util.CuratorUtils;
import java.net.InetSocketAddress;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;

/**
 * 基于zookeeper的服务发现实现
 *
 * @author cao wei
 */
@Slf4j
public class ZkServiceDiscovery implements ServiceDiscovery {

    private static final ProtocolConfig protocolConfig;
    private final LoadBalance loadBalance;

    static {
        protocolConfig = ConfigCache.getConfig(ProtocolConfig.class);
    }

    public ZkServiceDiscovery() {
        this.loadBalance = ExtensionLoader.getExtensionLoader(LoadBalance.class)
            .getExtension(protocolConfig.getLoadBalanceType().getType());
    }

    @Override
    public InetSocketAddress lookupService(String rpcServiceName) {
        CuratorFramework zkClient = CuratorUtils.getZkClient();
        List<String> serviceUrlList = CuratorUtils.getChildrenNodes(zkClient, rpcServiceName);
        if (serviceUrlList == null || serviceUrlList.size() == 0) {
            throw new RpcException(RpcErrorMessageEnum.SERVICE_CAN_NOT_BE_FOUND, rpcServiceName);
        }
        // 负载均衡算法挑选出合适的服务
        String targetServiceUrl = loadBalance.selectServiceAddress(serviceUrlList, rpcServiceName);
        log.info("Successfully found the service address:[{}]", targetServiceUrl);
        String[] socketAddressArray = targetServiceUrl.split(":");
        String host = socketAddressArray[0];
        int port = Integer.parseInt(socketAddressArray[1]);
        return new InetSocketAddress(host, port);
    }

    @Override
    public void subscribe(RpcServiceProperties properties) {

    }

    @Override
    public void unsubscribe(RpcServiceProperties properties) {

    }
}
