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
 * UserAnswerDet查询模型
 */
@NoArgsConstructor
@Data
public class UserAnswerDetPagedInput extends PagedInput {
    
    /**
     * Id主键
     */
    @JsonProperty("Id")
    private Integer Id;
    /**
     * 回答内容模糊查询条件
     */
  	 @JsonProperty("UserAnswer")
    private String UserAnswer;
    /**
     * 正确回答模糊查询条件
     */
  	 @JsonProperty("RightAnswer")
    private String RightAnswer;
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
     * 题型
     */
  	 @JsonProperty("QuestionType")
    private Integer QuestionType;
     /**
     * 是否正确
     */
  	 @JsonProperty("IsRight")
    private Boolean IsRight;

}
