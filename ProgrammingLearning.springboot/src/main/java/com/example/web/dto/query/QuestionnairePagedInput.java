package com.example.web.dto.query;

import com.example.web.tools.dto.PagedInput;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.util.List;

/**
 * Questionnaire查询模型
 */
@NoArgsConstructor
@Data
public class QuestionnairePagedInput extends PagedInput {
    
    /**
     * Id主键
     */
    @JsonProperty("Id")
    private Integer Id;
    /**
     * 问卷标题模糊查询条件
     */
  	 @JsonProperty("Title")
    private String Title;
    /**
     * 小标题模糊查询条件
     */
  	 @JsonProperty("SmallTitle")
    private String SmallTitle;
    /**
     * 问卷类型模糊查询条件
     */
  	 @JsonProperty("Type")
    private String Type;
     /**
     * 发布人
     */
  	 @JsonProperty("PublishUserId")
    private Integer PublishUserId;
    /**
     * 是否限时时间范围
     */
    @JsonProperty("IsTimeLimitRange")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private List<LocalDateTime> IsTimeLimitRange;

}
