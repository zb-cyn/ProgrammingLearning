package com.example.web.dto;
import com.example.web.enums.*;
import com.example.web.tools.dto.BaseDto;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.sql.Date;
import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.example.web.entity.*;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
/**
 * 常见问题手册类
 */
@Data
public class AskedQuestionsDto extends BaseDto
{

    
     
    /**
     * 标题
     */ 
    @JsonProperty("Title")
    private String Title;
    
     
    /**
     * 类型
     */ 
    @JsonProperty("Type")
    private String Type;
    
     
    /**
     * 内容
     */ 
    @JsonProperty("Content")
    private String Content;
    
     
    /**
     * 有效次数
     */ 
    @JsonProperty("AvailCount")
    private Integer AvailCount;          
    
     
    /**
     * 无效次数
     */ 
    @JsonProperty("NoAvailCount")
    private Integer NoAvailCount;          

 	 /**
     * 把常见问题手册传输模型转换成常见问题手册实体
     */
    public AskedQuestions MapToEntity() throws InvocationTargetException, IllegalAccessException {
        AskedQuestions AskedQuestions= new AskedQuestions();
     
         BeanUtils.copyProperties(AskedQuestions,this);
        
        return AskedQuestions;
    }

}
