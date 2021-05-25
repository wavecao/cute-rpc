package com.rpc.spring.support;

import com.rpc.RpcBootstrap;
import com.rpc.remoting.transport.netty.client.NettyRpcClient;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author cao wei
 * @since 2021/05/25
 */
public class RpcClientBootstrap extends RpcBootstrap implements InitializingBean {

  @Override
  public void afterPropertiesSet() throws Exception {
    super.init();
    NettyRpcClient rpcClient = new NettyRpcClient();
  }
}
