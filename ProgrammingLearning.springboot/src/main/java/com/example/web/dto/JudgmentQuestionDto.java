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
 * 判断题型类
 */
@Data
public class JudgmentQuestionDto extends BaseDto
{

    
     
    /**
     * 题干
     */ 
    @JsonProperty("Title")
    private String Title;
    
     
    /**
     * 正确答案
     */ 
    @JsonProperty("RightAnswer")
    private String RightAnswer;
    
     
    /**
     * 解析
     */ 
    @JsonProperty("AnalyzeResult")
    private String AnalyzeResult;
    
     
    /**
     * 分类
     */ 
    @JsonProperty("Type")
    private String Type;

 	 /**
     * 把判断题型传输模型转换成判断题型实体
     */
    public JudgmentQuestion MapToEntity() throws InvocationTargetException, IllegalAccessException {
        JudgmentQuestion JudgmentQuestion= new JudgmentQuestion();
        BeanUtils.copyProperties(JudgmentQuestion,this);
        return JudgmentQuestion;
    }

}
