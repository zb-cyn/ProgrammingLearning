package com.example.web.enums;

import java.util.HashMap;

 /**
   *题型枚举枚举
   */
public enum QuestionTypeEnum 
  {
    /**
     * 单选题
     */    
    单选题(1), 
     
    /**
     * 多选题
     */    
    多选题(2), 
     
    /**
     * 判断题
     */    
    判断题(3), 
     
    /**
     * 主观题
     */    
    主观题(4); 
     
            
    private final int index;
    
    QuestionTypeEnum(int index) 
    {
      this.index = index;
    }

    public int index() {
      return index;
    }
     private static final HashMap<Integer,QuestionTypeEnum> MY_MAP = new HashMap<Integer,QuestionTypeEnum>();
     static {
            for (QuestionTypeEnum myEnum : values()) {
                MY_MAP.put(myEnum.index(), myEnum);
            }
      }
     public static QuestionTypeEnum GetEnum(Integer v)
        {
           if(v==null){
                return MY_MAP.values().stream().findFirst().get();
            }
            return MY_MAP.get(v);
        }
     
 }
