package com.rpc.registry.zk;

import com.rpc.entity.RpcServiceProperties;
import com.rpc.registry.ServiceRegistry;
import com.rpc.registry.zk.util.CuratorUtils;
import java.net.InetSocketAddress;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;

/**
 * 基于zookeeper实现服务注册
 * @author cao wei
 */
@Slf4j
public class ZkServiceRegistry implements ServiceRegistry {

    @Override
    public void registerService(String rpcServiceName, InetSocketAddress inetSocketAddress) {
        String servicePath = CuratorUtils.ZK_REGISTER_ROOT_PATH + "/" + rpcServiceName + inetSocketAddress.toString();
        CuratorFramework zkClient = CuratorUtils.getZkClient();
        CuratorUtils.createPersistentNode(zkClient, servicePath);
    }

    @Override
    public void unregister(String rpcServiceName, InetSocketAddress inetSocketAddress) {

    }

    @Override
    public void offline(RpcServiceProperties properties) {

    }

    @Override
    public void shutdown(RpcServiceProperties properties) {

    }

    @Override
    public boolean isShutDown(RpcServiceProperties properties) {
        return false;
    }
}
