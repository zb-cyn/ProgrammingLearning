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
 * 主观题类
 */
@Data
public class SubjectiveQuestionDto extends BaseDto
{

    
     
    /**
     * 标题
     */ 
    @JsonProperty("Title")
    private String Title;
    
     
    /**
     * 内容
     */ 
    @JsonProperty("Content")
    private String Content;
    
     
    /**
     * 解释
     */ 
    @JsonProperty("AnalyzeResult")
    private String AnalyzeResult;
    
          
    /**
     * 正确答案
     */ 
    @JsonProperty("RightAnswer")
    private String RightAnswer;
    /**
     * 类型
     */ 
    @JsonProperty("Type")
    private String Type;

 	 /**
     * 把主观题传输模型转换成主观题实体
     */
    public SubjectiveQuestion MapToEntity() throws InvocationTargetException, IllegalAccessException {
        SubjectiveQuestion SubjectiveQuestion= new SubjectiveQuestion();
     
         BeanUtils.copyProperties(SubjectiveQuestion,this);
        
        return SubjectiveQuestion;
    }

}
