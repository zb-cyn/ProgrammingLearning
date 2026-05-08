package com.example.web.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.poi.hpsf.Decimal;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.sql.Date;
import java.sql.Timestamp;
import lombok.Data;
import java.time.LocalDateTime;
import com.example.web.dto.*;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
/**
 * 常见问题手册表
 */
@Data
@TableName("`AskedQuestions`")
public class AskedQuestions extends BaseEntity {

      
  	  /**
     * 标题
     */  
    @JsonProperty("Title")
    @TableField(value="Title",updateStrategy = FieldStrategy.IGNORED)
    private String Title;
      
  	  /**
     * 类型
     */  
    @JsonProperty("Type")
    @TableField(value="Type",updateStrategy = FieldStrategy.IGNORED)
    private String Type;
      
    /**
     * 内容
     */  
    @JsonProperty("Content")
     @TableField(value="Content",updateStrategy = FieldStrategy.IGNORED)
    private String Content;
      
    /**
     * 有效次数
     */  
    @JsonProperty("AvailCount")
    @TableField(value="AvailCount",updateStrategy = FieldStrategy.IGNORED)
    private Integer AvailCount;          
      
    /**
     * 无效次数
     */  
    @JsonProperty("NoAvailCount")
    @TableField(value="NoAvailCount",updateStrategy = FieldStrategy.IGNORED)
    private Integer NoAvailCount;          
  
    /**
     * 把常见问题手册实体转换成常见问题手册传输模型
     */
    public AskedQuestionsDto MapToDto() throws InvocationTargetException, IllegalAccessException {
        AskedQuestionsDto AskedQuestionsDto = new AskedQuestionsDto();
       
        BeanUtils.copyProperties(AskedQuestionsDto,this);
       
        return AskedQuestionsDto;
    }

}
