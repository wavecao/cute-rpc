package com.rpc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 容错类型枚举
 * @author cao wei
 * @since 2021/05/19
 */
@AllArgsConstructor
@Getter
public enum ClusterType {

  NONE(0, "none"),
  FAIL_FAST(1,"fail-fast"),
  SAFE_FAST(2, "fail-safe");


  private final int code;
  private final String type;

  public static ClusterType getByType(String type) {
    for (ClusterType value : ClusterType.values()) {
      if (value.type.equals(type)) {
        return value;
      }
    }
    return null;
  }
}
