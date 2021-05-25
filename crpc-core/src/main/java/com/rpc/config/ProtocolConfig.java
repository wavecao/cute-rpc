package com.rpc.config;

import com.rpc.enums.ClusterType;
import com.rpc.enums.LoadBalanceType;
import com.rpc.enums.SerializationTypeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 传输中的协议配置
 * @author cao wei
 * @since 2021/05/19
 */
@Setter
@Getter
public class ProtocolConfig {

  private SerializationTypeEnum serializationType = SerializationTypeEnum.HESSIAN;
  private LoadBalanceType loadBalanceType = LoadBalanceType.RANDOM;
  private ClusterType clusterType = ClusterType.FAIL_FAST;
  private int retryTimes;

  public ProtocolConfig() {
  }

  /**
   * 默认的配置，如果用户没有自定义，这里就是默认配置
   * @return
   */
  public static ProtocolConfig defaultConfig() {
    return new ProtocolConfig();
  }

}
