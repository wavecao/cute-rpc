package com.example.exampleserverspringboot.config;

import com.rpc.spring.support.RpcServerBootstrap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author cao wei
 * @since 2021/05/25
 */
@Configuration
public class CuteRpcConfig {

  @Bean
  public RpcServerBootstrap getRpcServer() {
    return new RpcServerBootstrap();
  }
}
