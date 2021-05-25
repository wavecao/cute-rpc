package com.rpc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * rpc框架设置相关的枚举
 * @author cao wei
 * @since 2021/03/23
 */
@AllArgsConstructor
@Getter
public enum RpcConfigEnum {

    RPC_CONFIG_PATH("rpc.properties"),
    ZK_ADDRESS("rpc.zookeeper.address");

    private final String propertyValue;

}
