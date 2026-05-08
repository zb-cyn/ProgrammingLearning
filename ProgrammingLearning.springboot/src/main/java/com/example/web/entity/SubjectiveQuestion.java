package com.example.web.entity;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.web.dto.SubjectiveQuestionDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 主观题表
 */
@Data
@TableName("`SubjectiveQuestion`")
public class SubjectiveQuestion extends BaseEntity {

    /**
     * 标题
     */
    @JsonProperty("Title")
    @TableField(value = "Title", updateStrategy = FieldStrategy.IGNORED)
    private String Title;

    /**
     * 内容
     */
    @JsonProperty("Content")
    @TableField(value = "Content", updateStrategy = FieldStrategy.IGNORED)
    private String Content;

    /**
     * 解释
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
     * 类型
     */
    @JsonProperty("Type")
    @TableField(value = "Type", updateStrategy = FieldStrategy.IGNORED)
    private String Type;

    /**
     * 把主观题实体转换成主观题传输模型
     */
    public SubjectiveQuestionDto MapToDto() throws InvocationTargetException, IllegalAccessException {
        SubjectiveQuestionDto SubjectiveQuestionDto = new SubjectiveQuestionDto();

        BeanUtils.copyProperties(SubjectiveQuestionDto, this);

        return SubjectiveQuestionDto;
    }

}
