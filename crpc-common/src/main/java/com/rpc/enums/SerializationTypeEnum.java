package com.rpc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * rpc序列化方式的枚举
 * @author cao wei
 * @since 2021/03/22
 */
@AllArgsConstructor
@Getter
public enum SerializationTypeEnum {

    // 采用16进制表示
    KYRO((byte) 0x01, "kyro"),
    PROTOSTUFF((byte) 0x02, "protostuff"),
    HESSIAN((byte) 0x03, "hessian");

    private final byte code;
    private final String type;

    public static String getName(byte code) {
        for (SerializationTypeEnum c : SerializationTypeEnum.values()) {
            if (c.getCode() == code) {
                return c.type;
            }
        }
        return null;
    }

    public static SerializationTypeEnum getByType(String type) {
        for (SerializationTypeEnum value : SerializationTypeEnum.values()) {
            if (value.type.equals(type)) {
                return value;
            }
        }
        return null;
    }

}
