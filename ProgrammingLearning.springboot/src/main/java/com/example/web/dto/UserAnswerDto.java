package com.example.web.dto;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.example.web.entity.UserAnswer;
import com.example.web.tools.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Data;

/**
 * 用户提交答题类
 */
@Data
public class UserAnswerDto extends BaseDto {

    /**
     * 用户
     */
    @JsonProperty("UserId")
    private Integer UserId;

    /**
     * 提交时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("SubmitTime")
    private LocalDateTime SubmitTime;

    /**
     * 开始时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("BeginTime")
    private LocalDateTime BeginTime;

    /**
     * 问卷
     */
    @JsonProperty("QuestionnaireId")
    private Integer QuestionnaireId;

    /**
     * 总得分
     */
    @JsonProperty("TotalScore")
    private Double TotalScore;

    @JsonProperty("UserDto")
    private AppUserDto UserDto;

    @JsonProperty("QuestionnaireDto")
    private QuestionnaireDto QuestionnaireDto;

    /**
     * 问卷明细
     */
    @JsonProperty("UserAnswerDetDtoList")
    private List<UserAnswerDetDto> UserAnswerDetDtoList;

    /**
     * 单选题个数
     */
    @JsonProperty("SingleChoiseQuestionCount")
    private Integer SingleChoiseQuestionCount;

    /**
     * 多选题个数
     */
    @JsonProperty("MultipleChoiceQuestionCount")
    private Integer MultipleChoiceQuestionCount;

    /**
     * 判断题个数
     */
    @JsonProperty("JudgmentQuestionCount")
    private Integer JudgmentQuestionCount;

    /**
     * 主观题个数
     */
    @JsonProperty("SubjectiveQuestionCount")
    private Integer SubjectiveQuestionCount;

    /**
     * 单选题总分数
     */
    @JsonProperty("SingleChoiseQuestionTotalScore")
    private Double SingleChoiseQuestionTotalScore;

    /**
     * 
     * 多选题总分数
     */
    @JsonProperty("MultipleChoiceQuestionTotalScore")
    private Double MultipleChoiceQuestionTotalScore;

    /**
     * 判断题总分数
     */
    @JsonProperty("JudgmentQuestionTotalScore")
    private Double JudgmentQuestionTotalScore;

    /**
     * 主观题总分数
     */
    @JsonProperty("SubjectiveQuestionTotalScore")
    private Double SubjectiveQuestionTotalScore;

    /**
     * 单选题得分
     */
    @JsonProperty("SingleChoiseQuestionGetScore")
    private Double SingleChoiseQuestionGetScore;

    /**
     * 多选题得分
     */
    @JsonProperty("MultipleChoiceQuestionGetScore")
    private Double MultipleChoiceQuestionGetScore;

    /**
     * 判断题得分
     */
    @JsonProperty("JudgmentQuestionGetScore")
    private Double JudgmentQuestionGetScore;

    /**
     * 主观题得分
     */
    @JsonProperty("SubjectiveQuestionGetScore")
    private Double SubjectiveQuestionGetScore;

    /**
     * 考试Id
     */
    @JsonProperty("TestId")
    private Integer TestId;

    @JsonProperty("TestDto")
    private TestDto TestDto;

    /**
     * 是否阅卷完毕
     */
    @JsonProperty("IsMarking")
    private Boolean IsMarking;

    /**
     * 考试日志
     */
    @JsonProperty("UserAnswerLogDtoList")
    private List<UserAnswerLogDto> UserAnswerLogDtoList;

    /**
     * 把用户提交答题传输模型转换成用户提交答题实体
     */
    public UserAnswer MapToEntity() throws InvocationTargetException, IllegalAccessException {
        UserAnswer UserAnswer = new UserAnswer();
        BeanUtils.copyProperties(UserAnswer, this);
        return UserAnswer;
    }

}
