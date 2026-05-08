package com.example.web.dto;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.example.web.entity.QuestionnaireDet;
import com.example.web.enums.QuestionTypeEnum;
import com.example.web.tools.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 问卷明细类
 */
@Data
public class QuestionnaireDetDto extends BaseDto {

    /**
     * 问卷
     */
    @JsonProperty("QuestionnaireId")
    private Integer QuestionnaireId;

    @JsonProperty("SingleChoiseQuestionDto")
    private SingleChoiseQuestionDto SingleChoiseQuestionDto;

    @JsonProperty("MultipleChoiceQuestionDto")
    private MultipleChoiceQuestionDto MultipleChoiceQuestionDto;

    @JsonProperty("JudgmentQuestionDto")
    private JudgmentQuestionDto JudgmentQuestionDto;

    @JsonProperty("SubjectiveQuestionDto")
    private SubjectiveQuestionDto SubjectiveQuestionDto;

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
     * 分数
     */
    @JsonProperty("Score")
    private Double Score;

    @JsonProperty("QuestionnaireDto")
    private QuestionnaireDto QuestionnaireDto;

    @JsonProperty("Sort")
    private Integer Sort;

    /**
     * 把问卷明细传输模型转换成问卷明细实体
     */
    public QuestionnaireDet MapToEntity() throws InvocationTargetException, IllegalAccessException {
        QuestionnaireDet QuestionnaireDet = new QuestionnaireDet();
        BeanUtils.copyProperties(QuestionnaireDet, this);
        return QuestionnaireDet;
    }

}
