package com.rpc;

import com.rpc.config.ClientConfig;
import com.rpc.config.ProtocolConfig;
import com.rpc.config.ServerConfig;
import com.rpc.config.cache.ConfigCache;
import com.rpc.config.constants.RpcConfigConstants;
import com.rpc.enums.ClusterType;
import com.rpc.enums.LoadBalanceType;
import com.rpc.enums.SerializationTypeEnum;
import com.rpc.utils.PropertiesReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Rpc初始化的类
 * @author cao wei
 * @since 2021/05/22
 */
public class RpcBootstrap {

  private static final Logger logger = LoggerFactory.getLogger(RpcBootstrap.class);

  private ClientConfig clientConfig;
  private ServerConfig serverConfig;

  public void init() {
    try {
      initProtocolConfig();
      initServerConfig();
      initClientConfig();
    } catch (Exception e) {
      logger.error("初始化配置失败：{}", e.getMessage());
    }
  }

  private static void initProtocolConfig() {
    // 默认的配置
    ProtocolConfig protocolConfig = ProtocolConfig.defaultConfig();
    // 用户自定义的负载均衡配置
    LoadBalanceType loadBalanceType = LoadBalanceType
        .getByType(PropertiesReader.get(RpcConfigConstants.loadBalanceType));
    if (loadBalanceType != null) {
      protocolConfig.setLoadBalanceType(loadBalanceType);
    }

    SerializationTypeEnum serializationTypeEnum = SerializationTypeEnum
        .getByType(PropertiesReader.get(RpcConfigConstants.serializerType));
    if (serializationTypeEnum != null) {
      protocolConfig.setSerializationType(serializationTypeEnum);
    }

    ClusterType clusterType = ClusterType
        .getByType(PropertiesReader.get(RpcConfigConstants.clusterType));
    if (clusterType != null) {
      protocolConfig.setClusterType(clusterType);
    }

    Integer retryTimes = PropertiesReader.getInteger(RpcConfigConstants.retryTimes);
    if (retryTimes != null) {
      if (retryTimes >= 0 && retryTimes <= 10) {
        protocolConfig.setRetryTimes(retryTimes);
      }
    }

    ConfigCache.putIntoCache(protocolConfig);
  }

  private static void initClientConfig() {

  }

  private static void initServerConfig() {
    ServerConfig serverConfig = ServerConfig.defaultConfig();
    Integer port = PropertiesReader.getInteger(RpcConfigConstants.serverPort);
    if (port != null) {
      if (port >=1000 && port <= 9999) {
        serverConfig.setRpcServerPort(port);
      }
    }
    ConfigCache.putIntoCache(serverConfig);
  }

}
