package com.example.config;

import com.rpc.spring.support.RpcClientBootstrap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author cao wei
 * @since 2021/05/25
 */
@Configuration
public class CuteRpcConfig {


  @Bean
  public RpcClientBootstrap getRpcClient() {
    return new RpcClientBootstrap();
  }
}
