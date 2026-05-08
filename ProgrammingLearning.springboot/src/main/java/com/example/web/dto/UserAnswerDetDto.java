package com.example.web.dto;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.example.web.entity.UserAnswerDet;
import com.example.web.enums.QuestionTypeEnum;
import com.example.web.tools.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 用户提交答题明细类
 */
@Data
public class UserAnswerDetDto extends BaseDto {

    /**
     * 用户提交答题
     */
    @JsonProperty("UserAnswerId")
    private Integer UserAnswerId;

    /**
     * 问卷
     */
    @JsonProperty("QuestionnaireId")
    private Integer QuestionnaireId;

    /**
     * 题目
     */
    @JsonProperty("QuestionId")
    private Integer QuestionId;

    /**
     * 题型
     */
    @JsonProperty("QuestionType")
    private Integer QuestionType;

    public String getQuestionTypeFormat() {
        return QuestionTypeEnum.GetEnum(QuestionType).toString();
    }

    private String QuestionTypeFormat;

    /**
     * 回答内容
     */
    @JsonProperty("UserAnswer")
    private String UserAnswer;

    /**
     * 是否正确
     */
    @JsonProperty("IsRight")
    private Boolean IsRight;

    /**
     * 正确回答
     */
    @JsonProperty("RightAnswer")
    private String RightAnswer;


 



    /**
     * 得分
     */
    @JsonProperty("GetScore")
    private Double GetScore;

    /**
     * 分值
     */
    @JsonProperty("TotalScore")
    private Double TotalScore;

    @JsonProperty("UserAnswerDto")
    private AppUserDto UserAnswerDto;

    @JsonProperty("QuestionnaireDto")
    private QuestionnaireDto QuestionnaireDto;

    @JsonProperty("SingleChoiseQuestionDto")
    private SingleChoiseQuestionDto SingleChoiseQuestionDto;

    @JsonProperty("MultipleChoiceQuestionDto")
    private MultipleChoiceQuestionDto MultipleChoiceQuestionDto;

    @JsonProperty("JudgmentQuestionDto")
    private JudgmentQuestionDto JudgmentQuestionDto;

    @JsonProperty("SubjectiveQuestionDto")
    private SubjectiveQuestionDto SubjectiveQuestionDto;

    @JsonProperty("Sort")
    private Integer Sort;

    /**
     * 把用户提交答题明细传输模型转换成用户提交答题明细实体
     */
    public UserAnswerDet MapToEntity() throws InvocationTargetException, IllegalAccessException {
        UserAnswerDet UserAnswerDet = new UserAnswerDet();
        BeanUtils.copyProperties(UserAnswerDet, this);
        return UserAnswerDet;
    }

}
