package com.rpc.exception;

/**
 * 序列化过程出现的异常
 * @author cao wei
 * @since 2021/03/27
 */
public class SerializeException extends RuntimeException {
    public SerializeException(String message) {
        super(message);
    }
}
