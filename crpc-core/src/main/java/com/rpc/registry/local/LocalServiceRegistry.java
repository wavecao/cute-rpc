package com.rpc.registry.local;

import com.rpc.entity.RpcServiceProperties;
import com.rpc.registry.ServiceRegistry;
import java.net.InetSocketAddress;

/**
 * 本地注册中心，可选进行数据库的持久化
 * @author cao wei
 * @since 2021/04/25
 */
public class LocalServiceRegistry implements ServiceRegistry {

  @Override
  public void registerService(String rpcServiceName, InetSocketAddress inetSocketAddress) {

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
