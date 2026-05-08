package com.example.web.entity;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.web.dto.UserAnswerDetDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 用户提交答题明细表
 */
@Data
@TableName("`UserAnswerDet`")
public class UserAnswerDet extends BaseEntity {

    /**
     * 用户提交答题
     */
    @JsonProperty("UserAnswerId")
    @TableField(value = "UserAnswerId", updateStrategy = FieldStrategy.IGNORED)
    private Integer UserAnswerId;

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
     * 回答内容
     */
    @JsonProperty("UserAnswer")
    @TableField(value = "UserAnswer", updateStrategy = FieldStrategy.IGNORED)
    private String UserAnswer;

    /**
     * 是否正确
     */
    @JsonProperty("IsRight")
    @TableField(value = "IsRight", updateStrategy = FieldStrategy.IGNORED)
    private Boolean IsRight;

    /**
     * 正确回答
     */
    @JsonProperty("RightAnswer")
    @TableField(value = "RightAnswer", updateStrategy = FieldStrategy.IGNORED)
    private String RightAnswer;

    /**
     * 得分
     */
    @JsonProperty("GetScore")
    @TableField(value = "GetScore", updateStrategy = FieldStrategy.IGNORED)
    private Double GetScore;

    /**
     * 分值
     */
    @JsonProperty("TotalScore")
    @TableField(value = "TotalScore", updateStrategy = FieldStrategy.IGNORED)
    private Double TotalScore;

    /**
     * 顺序
     */
    @JsonProperty("Sort")
    @TableField(value = "Sort", updateStrategy = FieldStrategy.IGNORED)
    private Integer Sort;

    /**
     * 把用户提交答题明细实体转换成用户提交答题明细传输模型
     */
    public UserAnswerDetDto MapToDto() throws InvocationTargetException, IllegalAccessException {
        UserAnswerDetDto UserAnswerDetDto = new UserAnswerDetDto();
        BeanUtils.copyProperties(UserAnswerDetDto, this);
        return UserAnswerDetDto;
    }

}
