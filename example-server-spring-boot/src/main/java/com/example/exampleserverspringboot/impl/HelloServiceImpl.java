package com.example.exampleserverspringboot.impl;

import com.rpc.Hello;
import com.rpc.HelloService;
import com.rpc.annotation.RpcService;
import com.rpc.utils.NetUtil;

/**
 * @author cao wei
 * @since 2021/05/25
 */
@RpcService
public class HelloServiceImpl implements HelloService {

  @Override
  public String hello(Hello hello) {
    return hello.getMessage() + "来自于SpringBootExample，来自于：" + NetUtil.getLocalAddress();
  }
}
