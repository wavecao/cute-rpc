package com.rpc.spring.support;

import com.rpc.RpcBootstrap;
import com.rpc.remoting.transport.netty.server.NettyRpcServer;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 对Spring的支持
 * @author cao wei
 * @since 2021/05/25
 */
public class RpcServerBootstrap extends RpcBootstrap implements InitializingBean , DisposableBean {

  private NettyRpcServer rpcServer;

  @Override
  public void afterPropertiesSet() throws Exception {
    super.init();
    rpcServer = new NettyRpcServer();
    rpcServer.start();
  }

  @Override
  public void destroy() throws Exception {
    rpcServer.stop();
  }
}
