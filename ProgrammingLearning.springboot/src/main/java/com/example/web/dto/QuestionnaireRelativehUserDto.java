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
 * 问卷指定用户类
 */
@Data
public class QuestionnaireRelativehUserDto extends BaseDto
{

    
     
    /**
     * 用户
     */ 
    @JsonProperty("UserId")
    private Integer UserId;          
    
     
    /**
     * 问卷
     */ 
    @JsonProperty("QuestionnaireId")
    private Integer QuestionnaireId;          

 	 /**
     * 把问卷指定用户传输模型转换成问卷指定用户实体
     */
    public QuestionnaireRelativehUser MapToEntity() throws InvocationTargetException, IllegalAccessException {
        QuestionnaireRelativehUser QuestionnaireRelativehUser= new QuestionnaireRelativehUser();
     
         BeanUtils.copyProperties(QuestionnaireRelativehUser,this);
        
        return QuestionnaireRelativehUser;
    }

}
