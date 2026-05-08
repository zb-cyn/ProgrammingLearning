package com.example.web.dto;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.example.web.entity.Questionnaire;
import com.example.web.tools.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 问卷类
 */
@Data
public class QuestionnaireDto extends BaseDto {

    /**
     * 问卷标题
     */
    @JsonProperty("Title")
    private String Title;

    /**
     * 小标题
     */
    @JsonProperty("SmallTitle")
    private String SmallTitle;

    /**
     * 是否限时
     */
    @JsonProperty("IsTimeLimit")
    private Boolean IsTimeLimit;

    /**
     * 分钟
     */
    @JsonProperty("LimitMinute")
    private Integer LimitMinute;

    /**
     * 问卷类型
     */
    @JsonProperty("Type")
    private String Type;

    /**
     * 发布人
     */
    @JsonProperty("PublishUserId")
    private Integer PublishUserId;

    @JsonProperty("PublishUserDto")
    private AppUserDto PublishUserDto;

    @JsonProperty("SingleChoiseQuestionIds")
    private List<Integer> SingleChoiseQuestionIds;

    @JsonProperty("MultipleChoiceQuestionIds")
    private List<Integer> MultipleChoiceQuestionIds;

    @JsonProperty("JudgmentQuestionIds")
    private List<Integer> JudgmentQuestionIds;

    @JsonProperty("QuestionnaireDetDtos")
    private List<QuestionnaireDetDto> QuestionnaireDetDtos;

    @JsonProperty("SubjectiveQuestionIds")
    private List<Integer> SubjectiveQuestionIds;

    @JsonProperty("SubjectiveQuestionDtos")
    private List<SubjectiveQuestionDto> SubjectiveQuestionDtos;

    @JsonProperty("SingleChoiseQuestionCount")
    private Integer SingleChoiseQuestionCount;

    @JsonProperty("MultipleChoiceQuestionCount")
    private Integer MultipleChoiceQuestionCount;

    @JsonProperty("JudgmentQuestionCount")
    private Integer JudgmentQuestionCount;

    @JsonProperty("SubjectiveQuestionCount")
    private Integer SubjectiveQuestionCount;

    @JsonProperty("QuestionLevel")
    private Integer QuestionLevel;

    /**
     * 把问卷传输模型转换成问卷实体
     */
    public Questionnaire MapToEntity() throws InvocationTargetException, IllegalAccessException {
        Questionnaire Questionnaire = new Questionnaire();
        BeanUtils.copyProperties(Questionnaire, this);
        return Questionnaire;
    }

}
