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
 * 问卷表
 */
@Data
@TableName("`Questionnaire`")
public class Questionnaire extends BaseEntity {


    /**
     * 问卷标题
     */
    @JsonProperty("Title")
    @TableField(value = "Title", updateStrategy = FieldStrategy.IGNORED)
    private String Title;

    /**
     * 小标题
     */
    @JsonProperty("SmallTitle")
    @TableField(value = "SmallTitle", updateStrategy = FieldStrategy.IGNORED)
    private String SmallTitle;

    /**
     * 是否限时
     */
    @JsonProperty("IsTimeLimit")
    @TableField(value = "IsTimeLimit", updateStrategy = FieldStrategy.IGNORED)
    private Boolean IsTimeLimit;

    /**
     * 分钟
     */
    @JsonProperty("LimitMinute")
    @TableField(value = "LimitMinute", updateStrategy = FieldStrategy.IGNORED)
    private Integer LimitMinute;

    /**
     * 问卷类型
     */
    @JsonProperty("Type")
    @TableField(value = "Type", updateStrategy = FieldStrategy.IGNORED)
    private String Type;

    /**
     * 发布人
     */
    @JsonProperty("PublishUserId")
    @TableField(value = "PublishUserId", updateStrategy = FieldStrategy.IGNORED)
    private Integer PublishUserId;


    /**
     * 把问卷实体转换成问卷传输模型
     */
    public QuestionnaireDto MapToDto() throws InvocationTargetException, IllegalAccessException {
        QuestionnaireDto QuestionnaireDto = new QuestionnaireDto();
        BeanUtils.copyProperties(QuestionnaireDto, this);
        return QuestionnaireDto;
    }

}
