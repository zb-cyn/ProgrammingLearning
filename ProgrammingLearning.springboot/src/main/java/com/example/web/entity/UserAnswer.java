package com.example.web.entity;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

import org.apache.commons.beanutils.BeanUtils;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.web.dto.UserAnswerDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Data;

/**
 * 用户提交答题表
 */
@Data
@TableName("`UserAnswer`")
public class UserAnswer extends BaseEntity {

    /**
     * 用户
     */
    @JsonProperty("UserId")
    @TableField(value = "UserId", updateStrategy = FieldStrategy.IGNORED)
    private Integer UserId;

    /**
     * 提交时间
     */
    @JsonProperty("SubmitTime")
    @TableField(value = "SubmitTime", updateStrategy = FieldStrategy.IGNORED)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime SubmitTime;

    /**
     * 开始时间
     */
    @JsonProperty("BeginTime")
    @TableField(value = "BeginTime", updateStrategy = FieldStrategy.IGNORED)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime BeginTime;

    /**
     * 问卷
     */
    @JsonProperty("QuestionnaireId")
    @TableField(value = "QuestionnaireId", updateStrategy = FieldStrategy.IGNORED)
    private Integer QuestionnaireId;

    /**
     * 总得分
     */
    @JsonProperty("TotalScore")
    @TableField(value = "TotalScore", updateStrategy = FieldStrategy.IGNORED)
    private Double TotalScore;

    /**
     * 考试Id
     */
    @JsonProperty("TestId")
    @TableField(value = "TestId", updateStrategy = FieldStrategy.IGNORED)
    private Integer TestId;

    /**
     * 是否阅卷完毕
     */
    @JsonProperty("IsMarking")
    @TableField(value = "IsMarking", updateStrategy = FieldStrategy.IGNORED)
    private Boolean IsMarking;

    /**
     * 把用户提交答题实体转换成用户提交答题传输模型
     */
    public UserAnswerDto MapToDto() throws InvocationTargetException, IllegalAccessException {
        UserAnswerDto UserAnswerDto = new UserAnswerDto();
        BeanUtils.copyProperties(UserAnswerDto, this);
        return UserAnswerDto;
    }

}
