package com.rpc.serialize.hessian;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.rpc.exception.SerializeException;
import com.rpc.serialize.Serializer;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cao wei
 * @since 2021/04/25
 */
public class HessianSerializer implements Serializer {

  private final static Logger logger = LoggerFactory.getLogger(HessianSerializer.class);

  @Override
  public byte[] serialize(Object obj) {
    HessianOutput hessianOutput = null;
    try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
      hessianOutput = new HessianOutput(byteArrayOutputStream);
      hessianOutput.writeObject(obj);
      return byteArrayOutputStream.toByteArray();
    } catch (IOException e) {
      logger.error("序列化时有错误发生:", e);
      throw new SerializeException("序列化时有错误发生");
    } finally {
      if (hessianOutput != null) {
        try {
          hessianOutput.close();
        } catch (IOException e) {
          logger.error("关闭流时有错误发生:", e);
        }
      }
    }
  }

  @Override
  public <T> T deserialize(byte[] bytes, Class<T> clazz) {
    HessianInput hessianInput = null;
    try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes)) {
      hessianInput = new HessianInput(byteArrayInputStream);
      return (T) hessianInput.readObject();
    } catch (IOException e) {
      logger.error("序列化时有错误发生:", e);
      throw new SerializeException("序列化时有错误发生");
    } finally {
      if (hessianInput != null) hessianInput.close();
    }
  }
}
