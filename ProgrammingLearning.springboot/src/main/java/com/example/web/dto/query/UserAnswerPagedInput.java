package com.example.web.dto.query;

import java.time.LocalDateTime;
import java.util.List;

import com.example.web.tools.dto.PagedInput;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserAnswer查询模型
 */
@NoArgsConstructor
@Data
public class UserAnswerPagedInput extends PagedInput {

    /**
     * Id主键
     */
    @JsonProperty("Id")
    private Integer Id;
    /**
     * 用户
     */
    @JsonProperty("UserId")
    private Integer UserId;
    /**
     * 问卷
     */
    @JsonProperty("QuestionnaireId")
    private Integer QuestionnaireId;
    /**
     * 提交时间时间范围
     */
    @JsonProperty("SubmitTimeRange")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private List<LocalDateTime> SubmitTimeRange;
    /**
     * 开始时间时间范围
     */
    @JsonProperty("BeginTimeRange")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private List<LocalDateTime> BeginTimeRange;

    @JsonProperty("TestId")
    private Integer TestId;


}
