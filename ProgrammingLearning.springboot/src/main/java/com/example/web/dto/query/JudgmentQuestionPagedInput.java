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
 * JudgmentQuestion查询模型
 */
@NoArgsConstructor
@Data
public class JudgmentQuestionPagedInput extends PagedInput {
    
    /**
     * Id主键
     */
    @JsonProperty("Id")
    private Integer Id;
    /**
     * 题干模糊查询条件
     */
  	 @JsonProperty("Title")
    private String Title;
    /**
     * 正确答案模糊查询条件
     */
  	 @JsonProperty("RightAnswer")
    private String RightAnswer;
    /**
     * 解析模糊查询条件
     */
  	 @JsonProperty("AnalyzeResult")
    private String AnalyzeResult;
    /**
     * 分类模糊查询条件
     */
  	 @JsonProperty("Type")
    private String Type;

}
