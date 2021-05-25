package com.rpc.remoting.dto;

import com.rpc.entity.RpcServiceProperties;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * rpc请求
 * @author cao wei
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = 1905122041950251207L;
    // 请求ID
    private String requestId;
    // 调用的接口名
    private String interfaceName;
    // 调用的具体方法名
    private String methodName;
    // 接口参数
    private Object[] parameters;
    // 接口参数类型
    private Class<?>[] paramTypes;
    // 服务版本
    private String version;
    // 服务分组
    private String group;

    public RpcServiceProperties toRpcProperties() {
        return RpcServiceProperties.builder().serviceName(this.getInterfaceName())
                .version(this.getVersion())
                .group(this.getGroup()).build();
    }
}
