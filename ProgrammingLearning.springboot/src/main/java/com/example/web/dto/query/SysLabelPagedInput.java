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
 * SysLabel查询模型
 */
@NoArgsConstructor
@Data
public class SysLabelPagedInput extends PagedInput {
    
    /**
     * Id主键
     */
    @JsonProperty("Id")
    private Integer Id;
    /**
     * 标签名称模糊查询条件
     */
  	 @JsonProperty("Name")
    private String Name;
    /**
     * 显示顺序模糊查询条件
     */
  	 @JsonProperty("Sort")
    private String Sort;
    /**
     * 编码模糊查询条件
     */
  	 @JsonProperty("Code")
    private String Code;

}
