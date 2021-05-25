package com.rpc.config;

import lombok.Getter;
import lombok.Setter;

/**
 * RPC服务端的相关配置
 * @author cao wei
 * @since 2021/05/19
 */
@Setter
@Getter
public class ServerConfig {

  private String rpcServerAddress = "127.0.0.1";
  private int rpcServerPort = 9998;

  public ServerConfig(){}

  public static ServerConfig defaultConfig() {
    return new ServerConfig();
  }
}

