package com.rpc.remoting.transport;

import com.rpc.extension.SPI;
import com.rpc.remoting.dto.RpcRequest;

/**
 * 发送请求
 * @author cao wei
 */
@SPI
public interface RpcRequestTransport {
    /**
     * send rpc request to server and get result
     *
     * @param rpcRequest message body
     * @return data from server
     */
    Object sendRpcRequest(RpcRequest rpcRequest);
}
