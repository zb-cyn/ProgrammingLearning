package com.example.web.entity;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.web.dto.QuestionnaireDetDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 问卷明细表
 */
@Data
@TableName("`QuestionnaireDet`")
public class QuestionnaireDet extends BaseEntity {

    /**
     * 问卷
     */
    @JsonProperty("QuestionnaireId")
    @TableField(value = "QuestionnaireId", updateStrategy = FieldStrategy.IGNORED)
    private Integer QuestionnaireId;

    /**
     * 题目
     */
    @JsonProperty("QuestionId")
    @TableField(value = "QuestionId", updateStrategy = FieldStrategy.IGNORED)
    private Integer QuestionId;

    /**
     * 题型
     */
    @JsonProperty("QuestionType")
    @TableField(value = "QuestionType", updateStrategy = FieldStrategy.IGNORED)
    private Integer QuestionType;

    /**
     * 分数
     */
    @JsonProperty("Score")
    @TableField(value = "Score", updateStrategy = FieldStrategy.IGNORED)
    private Double Score;

    /**
     * 排序
     */
    @JsonProperty("Sort")
    @TableField(value = "Sort", updateStrategy = FieldStrategy.IGNORED)
    private Integer Sort;

    /**
     * 把问卷明细实体转换成问卷明细传输模型
     */
    public QuestionnaireDetDto MapToDto() throws InvocationTargetException, IllegalAccessException {
        QuestionnaireDetDto QuestionnaireDetDto = new QuestionnaireDetDto();
        BeanUtils.copyProperties(QuestionnaireDetDto, this);
        return QuestionnaireDetDto;
    }

}
