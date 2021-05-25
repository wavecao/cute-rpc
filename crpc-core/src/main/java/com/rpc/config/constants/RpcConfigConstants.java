package com.rpc.config.constants;

/**
 *
 * @author cao wei
 * @since 2021/05/22
 */
public class RpcConfigConstants {

    /* 通用的配置常量 */
    private static final String CONFIG_PREFIX = "crpc.";
    private static final String CONFIG_SERVER = "server.";

    /* 传输相关配置的常量 */
    public static final String loadBalanceType = CONFIG_PREFIX + "loadbalance";

    public static final String serializerType = CONFIG_PREFIX + "serializer";

    public static final String clusterType = CONFIG_PREFIX + "cluster";

    public static final String retryTimes = CONFIG_PREFIX + "retry.times";

    /* 服务端配置的常量 */
    public static final String serverPort = CONFIG_PREFIX + CONFIG_SERVER + "port";

    public static final String serverAddr = CONFIG_PREFIX + CONFIG_SERVER + "address";

    /* 客户端配置的常量 */
}
