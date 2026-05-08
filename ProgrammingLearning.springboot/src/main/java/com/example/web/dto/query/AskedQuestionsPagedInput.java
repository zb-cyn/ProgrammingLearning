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
 * AskedQuestions查询模型
 */
@NoArgsConstructor
@Data
public class AskedQuestionsPagedInput extends PagedInput {
    
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
     * 类型模糊查询条件
     */
  	 @JsonProperty("Type")
    private String Type;
    /**
     * 内容模糊查询条件
     */
  	 @JsonProperty("Content")
    private String Content;

}
