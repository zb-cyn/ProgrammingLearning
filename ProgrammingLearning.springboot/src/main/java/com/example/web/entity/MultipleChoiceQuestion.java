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
 * 多选题表
 */
@Data
@TableName("`MultipleChoiceQuestion`")
public class MultipleChoiceQuestion extends BaseEntity {


    /**
     * 题干
     */
    @JsonProperty("Title")
    @TableField(value = "Title", updateStrategy = FieldStrategy.IGNORED)
    private String Title;

    /**
     * 选项A
     */
    @JsonProperty("OptionA")
    @TableField(value = "OptionA", updateStrategy = FieldStrategy.IGNORED)
    private String OptionA;

    /**
     * 选项B
     */
    @JsonProperty("OptionB")
    @TableField(value = "OptionB", updateStrategy = FieldStrategy.IGNORED)
    private String OptionB;

    /**
     * 选项C
     */
    @JsonProperty("OptionC")
    @TableField(value = "OptionC", updateStrategy = FieldStrategy.IGNORED)
    private String OptionC;

    /**
     * 选项D
     */
    @JsonProperty("OptionD")
    @TableField(value = "OptionD", updateStrategy = FieldStrategy.IGNORED)
    private String OptionD;

    /**
     * 解析
     */
    @JsonProperty("AnalyzeResult")
    @TableField(value = "AnalyzeResult", updateStrategy = FieldStrategy.IGNORED)
    private String AnalyzeResult;

    /**
     * 正确答案
     */
    @JsonProperty("RightAnswer")
    @TableField(value = "RightAnswer", updateStrategy = FieldStrategy.IGNORED)
    private String RightAnswer;

    /**
     * 分类
     */
    @JsonProperty("Type")
    @TableField(value = "Type", updateStrategy = FieldStrategy.IGNORED)
    private String Type;

    /**
     * 把多选题实体转换成多选题传输模型
     */
    public MultipleChoiceQuestionDto MapToDto() throws InvocationTargetException, IllegalAccessException {
        MultipleChoiceQuestionDto MultipleChoiceQuestionDto = new MultipleChoiceQuestionDto();
        BeanUtils.copyProperties(MultipleChoiceQuestionDto, this);
        return MultipleChoiceQuestionDto;
    }

}
