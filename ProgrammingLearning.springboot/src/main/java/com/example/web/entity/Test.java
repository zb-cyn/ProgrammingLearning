package com.example.web.entity;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

import org.apache.commons.beanutils.BeanUtils;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.web.dto.TestDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Data;

/**
 * 考试表
 */
@Data
@TableName("`Test`")
public class Test extends BaseEntity {

    /**
     * 问卷
     */
    @JsonProperty("QuestionnaireId")
    @TableField(value = "QuestionnaireId", updateStrategy = FieldStrategy.IGNORED)
    private Integer QuestionnaireId;

    /**
     * 起始有效时间
     */
    @JsonProperty("BeginActiveTime")
    @TableField(value = "BeginActiveTime", updateStrategy = FieldStrategy.IGNORED)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime BeginActiveTime;
    /**
     * 截至有效时间
     */
    @JsonProperty("EndActiveTime")
    @TableField(value = "EndActiveTime", updateStrategy = FieldStrategy.IGNORED)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime EndActiveTime;

    /**
     * 标题
     */
    @JsonProperty("Title")
    @TableField(value = "Title", updateStrategy = FieldStrategy.IGNORED)
    private String Title;

    /**
     * 是否随机打乱
     */
    @JsonProperty("IsRadom")
    @TableField(value = "IsRadom", updateStrategy = FieldStrategy.IGNORED)
    private Boolean IsRadom;

    /**
     * 把考试实体转换成考试传输模型
     */
    public TestDto MapToDto() throws InvocationTargetException, IllegalAccessException {
        TestDto TestDto = new TestDto();

        BeanUtils.copyProperties(TestDto, this);

        return TestDto;
    }

}
