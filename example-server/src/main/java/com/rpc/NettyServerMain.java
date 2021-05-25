package com.rpc;

import com.rpc.annotation.RpcScan;
import com.rpc.remoting.transport.netty.server.NettyRpcServer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Netty Server Test
 * @author cao wei
 */
@RpcScan(basePackage = {"com.rpc"})
public class NettyServerMain {

  public static void main(String[] args) {
    // Register service via annotation
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(NettyServerMain.class);
    NettyRpcServer nettyRpcServer = (NettyRpcServer) applicationContext.getBean("nettyRpcServer");
    nettyRpcServer.start();
  }
}
