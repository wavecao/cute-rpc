package com.rpc.cluster;

import com.rpc.extension.SPI;
import com.rpc.remoting.dto.RpcRequest;
import com.rpc.remoting.dto.RpcResponse;

/**
 * @author cao wei
 * @since 2021/05/21
 */
@SPI
public interface Cluster {

  RpcResponse onError(RpcRequest request, RuntimeException e);
}
