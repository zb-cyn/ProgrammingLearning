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
 * 问卷指定用户表
 */
@Data
@TableName("`QuestionnaireRelativehUser`")
public class QuestionnaireRelativehUser extends BaseEntity {

      
    /**
     * 用户
     */  
    @JsonProperty("UserId")
    @TableField(value="UserId",updateStrategy = FieldStrategy.IGNORED)
    private Integer UserId;          
      
    /**
     * 问卷
     */  
    @JsonProperty("QuestionnaireId")
    @TableField(value="QuestionnaireId",updateStrategy = FieldStrategy.IGNORED)
    private Integer QuestionnaireId;          
  
    /**
     * 把问卷指定用户实体转换成问卷指定用户传输模型
     */
    public QuestionnaireRelativehUserDto MapToDto() throws InvocationTargetException, IllegalAccessException {
        QuestionnaireRelativehUserDto QuestionnaireRelativehUserDto = new QuestionnaireRelativehUserDto();
       
        BeanUtils.copyProperties(QuestionnaireRelativehUserDto,this);
       
        return QuestionnaireRelativehUserDto;
    }

}
