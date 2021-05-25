package com.rpc.serialize.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rpc.remoting.dto.RpcRequest;
import com.rpc.serialize.Serializer;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cao wei
 * @since 2021/04/25
 */
public class JsonSerializer implements Serializer {

  private static final Logger logger = LoggerFactory.getLogger(JsonSerializer.class);

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public byte[] serialize(Object obj) {
    try {
      return objectMapper.writeValueAsBytes(obj);
    } catch (JsonProcessingException e) {
      logger.error("序列化时有错误发生: {}", e.getMessage());
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public <T> T deserialize(byte[] bytes, Class<T> clazz) {
    try {
      Object obj = objectMapper.readValue(bytes, clazz);
      if(obj instanceof RpcRequest) {
        obj = handleRequest(obj);
      }
      return (T) obj;
    } catch (IOException e) {
      logger.error("反序列化时有错误发生: {}", e.getMessage());
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 这里由于使用JSON序列化和反序列化Object数组，无法保证反序列化后仍然为原实例类型
   * 需要重新判断处理
   */
  private Object handleRequest(Object obj) throws IOException {
    RpcRequest rpcRequest = (RpcRequest) obj;
    for(int i = 0; i < rpcRequest.getParamTypes().length; i ++) {
      Class<?> clazz = rpcRequest.getParamTypes()[i];
      if(!clazz.isAssignableFrom(rpcRequest.getParameters()[i].getClass())) {
        byte[] bytes = objectMapper.writeValueAsBytes(rpcRequest.getParameters()[i]);
        rpcRequest.getParameters()[i] = objectMapper.readValue(bytes, clazz);
      }
    }
    return rpcRequest;
  }
}
