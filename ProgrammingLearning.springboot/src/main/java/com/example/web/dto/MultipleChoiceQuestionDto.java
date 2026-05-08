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
 * 多选题类
 */
@Data
public class MultipleChoiceQuestionDto extends BaseDto
{

    
     
    /**
     * 题干
     */ 
    @JsonProperty("Title")
    private String Title;
    
     
    /**
     * 选项A
     */ 
    @JsonProperty("OptionA")
    private String OptionA;
    
     
    /**
     * 选项B
     */ 
    @JsonProperty("OptionB")
    private String OptionB;
    
     
    /**
     * 选项C
     */ 
    @JsonProperty("OptionC")
    private String OptionC;
    
     
    /**
     * 选项D
     */ 
    @JsonProperty("OptionD")
    private String OptionD;
    
     
    /**
     * 解析
     */ 
    @JsonProperty("AnalyzeResult")
    private String AnalyzeResult;
    
     
    /**
     * 正确答案
     */ 
    @JsonProperty("RightAnswer")
    private String RightAnswer;
    
     
    /**
     * 分类
     */ 
    @JsonProperty("Type")
    private String Type;

 	 /**
     * 把多选题传输模型转换成多选题实体
     */
    public MultipleChoiceQuestion MapToEntity() throws InvocationTargetException, IllegalAccessException {
        MultipleChoiceQuestion MultipleChoiceQuestion= new MultipleChoiceQuestion();
        BeanUtils.copyProperties(MultipleChoiceQuestion,this);
        return MultipleChoiceQuestion;
    }

}
