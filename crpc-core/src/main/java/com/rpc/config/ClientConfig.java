package com.rpc.config;

import lombok.Getter;
import lombok.Setter;

/**
 * RPC客户端相关配置
 * @author cao wei
 * @since 2021/05/21
 */
@Getter
@Setter
public class ClientConfig {

  private int timeout = 1000;

  public ClientConfig() {

  }

  public static ClientConfig defaultConfig() {
    return new ClientConfig();
  }
}
