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
 * QuestionnaireDet查询模型
 */
@NoArgsConstructor
@Data
public class QuestionnaireDetPagedInput extends PagedInput {
    
    /**
     * Id主键
     */
    @JsonProperty("Id")
    private Integer Id;
     /**
     * 问卷
     */
  	 @JsonProperty("QuestionnaireId")
    private Integer QuestionnaireId;
     /**
     * 题型
     */
  	 @JsonProperty("QuestionType")
    private Integer QuestionType;

}
