package com.rpc.config.cache;

import com.rpc.exception.RpcException;
import com.rpc.factory.SingletonFactory;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cao wei
 * @since 2021/05/24
 */
public class ConfigCache {

  public static final Map<String, Object> CONFIG_CACHE = new ConcurrentHashMap<>();

  public static void putIntoCache(Object o) {
    if (o == null) {
      return;
    }
    Class<?> oClass = o.getClass();
    CONFIG_CACHE.put(o.toString(), o);
  }

  public static <T> T getConfig(Class<T> c) {
    if (c == null) {
      throw new IllegalArgumentException();
    }
    return c.cast(CONFIG_CACHE.getOrDefault(c.toString(), SingletonFactory.getInstance(c)));
  }
}
