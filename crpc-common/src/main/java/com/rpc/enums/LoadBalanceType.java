package com.rpc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author cao wei
 * @since 2021/05/19
 */
@AllArgsConstructor
@Getter
public enum LoadBalanceType {

  RANDOM(1, "RandomLB"),
  ROUND_ROBIN(2, "ConsistentHashLB");

  private final int code;
  private final String type;

  public static LoadBalanceType getByType(String type) {
    for (LoadBalanceType value : LoadBalanceType.values()) {
      if (value.type.equals(type)) {
        return value;
      }
    }
    return null;
  }
}
