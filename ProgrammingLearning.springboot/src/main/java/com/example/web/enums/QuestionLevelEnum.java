package com.example.web.enums;

import java.util.HashMap;

/**
 * 组卷难度枚举
 */
public enum QuestionLevelEnum {
  /**
   * 简单
   */
  简单(1),

  /**
   * 中等
   */
  中等(2),

  /**
   * 困难
   */
  困难(3);

  private final int index;

  QuestionLevelEnum(int index) {
    this.index = index;
  }

  public int index() {
    return index;
  }

  private static final HashMap<Integer, QuestionLevelEnum> MY_MAP = new HashMap<Integer, QuestionLevelEnum>();
  static {
    for (QuestionLevelEnum myEnum : values()) {
      MY_MAP.put(myEnum.index(), myEnum);
    }
  }

  public static QuestionLevelEnum GetEnum(Integer v) {
    if (v == null) {
      return MY_MAP.values().stream().findFirst().get();
    }
    return MY_MAP.get(v);
  }

}
