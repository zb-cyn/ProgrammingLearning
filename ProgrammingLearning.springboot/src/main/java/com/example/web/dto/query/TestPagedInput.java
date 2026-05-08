package com.example.web.dto.query;

import java.time.LocalDateTime;
import java.util.List;

import com.example.web.tools.dto.PagedInput;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Test查询模型
 */
@NoArgsConstructor
@Data
public class TestPagedInput extends PagedInput {

    /**
     * Id主键
     */
    @JsonProperty("Id")
    private Integer Id;
    /**
     * 标题模糊查询条件
     */
    @JsonProperty("Title")
    private String Title;

    /**
     * 问卷
     */
    @JsonProperty("QuestionnaireId")
    private Integer QuestionnaireId;
    /**
     * 有效时间时间范围
     */
    @JsonProperty("ActiveTimeRange")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private List<LocalDateTime> ActiveTimeRange;
    /**
     * 是否随机打乱
     */
    @JsonProperty("IsRadom")
    private Boolean IsRadom;

    /**
     * 我的
     */
    @JsonProperty("IsMy")
    private Boolean IsMy;

}
