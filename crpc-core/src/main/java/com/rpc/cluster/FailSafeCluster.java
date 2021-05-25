package com.rpc.cluster;

import com.rpc.enums.RpcResponseCodeEnum;
import com.rpc.remoting.dto.RpcRequest;
import com.rpc.remoting.dto.RpcResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 安全失败机制，拦击报错，响应默认的错误RpcResponse
 */
public class FailSafeCluster implements Cluster {

  private Logger logger = LoggerFactory.getLogger(FailSafeCluster.class);

  @Override
  public RpcResponse onError(RpcRequest request, RuntimeException e) {
    logger.error("FailSafeCluster, requestId: {}", request.getRequestId(), e);
    return RpcResponse.fail(RpcResponseCodeEnum.FAIL);
  }
}
