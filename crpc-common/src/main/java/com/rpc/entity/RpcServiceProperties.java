package com.rpc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Rpc中服务提供者的一些参数
 * @author cao wei
 * @since 2021/03/22
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class RpcServiceProperties {
  // 服务版本
  private String version;
  // 服务分组
  private String group;
  // 服务名称
  private String serviceName;
  // 服务权重
  private int wight;

  public String toRpcServiceName() {
    return this.getServiceName() + this.getGroup() + this.getVersion();
  }
}
