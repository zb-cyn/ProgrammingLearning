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
 * 判断题型表
 */
@Data
@TableName("`JudgmentQuestion`")
public class JudgmentQuestion extends BaseEntity {


    /**
     * 题干
     */
    @JsonProperty("Title")
    @TableField(value = "Title", updateStrategy = FieldStrategy.IGNORED)
    private String Title;

    /**
     * 正确答案
     */
    @JsonProperty("RightAnswer")
    @TableField(value = "RightAnswer", updateStrategy = FieldStrategy.IGNORED)
    private String RightAnswer;

    /**
     * 解析
     */
    @JsonProperty("AnalyzeResult")
    @TableField(value = "AnalyzeResult", updateStrategy = FieldStrategy.IGNORED)
    private String AnalyzeResult;

    /**
     * 分类
     */
    @JsonProperty("Type")
    @TableField(value = "Type", updateStrategy = FieldStrategy.IGNORED)
    private String Type;

    /**
     * 把判断题型实体转换成判断题型传输模型
     */
    public JudgmentQuestionDto MapToDto() throws InvocationTargetException, IllegalAccessException {
        JudgmentQuestionDto JudgmentQuestionDto = new JudgmentQuestionDto();
        BeanUtils.copyProperties(JudgmentQuestionDto, this);
        return JudgmentQuestionDto;
    }

}
