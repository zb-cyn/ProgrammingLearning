package com.example.web.dto;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

import org.apache.commons.beanutils.BeanUtils;

import com.example.web.entity.Test;
import com.example.web.tools.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Data;

/**
 * 考试类
 */
@Data
public class TestDto extends BaseDto {



    /**
     * 问卷
     */
    @JsonProperty("QuestionnaireId")
    private Integer QuestionnaireId;

    /**
     * 起始有效时间
     */
    @JsonProperty("BeginActiveTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime BeginActiveTime;
    /**
     * 截至有效时间
     */
    @JsonProperty("EndActiveTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime EndActiveTime;

    /**
     * 标题
     */
    @JsonProperty("Title")
    private String Title;

    /**
     * 是否随机打乱
     */
    @JsonProperty("IsRadom")
    private Boolean IsRadom;



    @JsonProperty("QuestionnaireDto")
    private QuestionnaireDto QuestionnaireDto;



    /**
     * 是否完成考试了
     * 
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @JsonProperty("IsFinish")
    private Boolean IsFinish;

    /**
     * 把考试传输模型转换成考试实体
     */
    public Test MapToEntity() throws InvocationTargetException, IllegalAccessException {
        Test Test = new Test();

        BeanUtils.copyProperties(Test, this);

        return Test;
    }

}
