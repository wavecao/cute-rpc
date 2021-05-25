package com.rpc.cluster;


import com.rpc.remoting.dto.RpcRequest;
import com.rpc.remoting.dto.RpcResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 快速失败机制，直接报错或者进行统一报错
 */
public class FailFastCluster implements Cluster {

  private Logger logger = LoggerFactory.getLogger(FailFastCluster.class);

  @Override
  public RpcResponse onError(RpcRequest request, RuntimeException e) {
    logger.error("FailFastCluster, requestId: {}", request.getRequestId(), e);
    throw e;
  }
}
