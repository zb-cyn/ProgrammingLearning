package com.example.web.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.poi.hpsf.Decimal;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.sql.Date;
import java.sql.Timestamp;
import lombok.Data;
import java.time.LocalDateTime;
import com.example.web.dto.*;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
/**
 * 系统标签表
 */
@Data
@TableName("`SysLabel`")
public class SysLabel extends BaseEntity {

      
  	  /**
     * 标签名称
     */  
    @JsonProperty("Name")
    @TableField(value="Name",updateStrategy = FieldStrategy.IGNORED)
    private String Name;
      
  	  /**
     * 显示顺序
     */  
    @JsonProperty("Sort")
    @TableField(value="Sort",updateStrategy = FieldStrategy.IGNORED)
    private String Sort;
      
  	  /**
     * 编码
     */  
    @JsonProperty("Code")
    @TableField(value="Code",updateStrategy = FieldStrategy.IGNORED)
    private String Code;
  
    /**
     * 把系统标签实体转换成系统标签传输模型
     */
    public SysLabelDto MapToDto() throws InvocationTargetException, IllegalAccessException {
        SysLabelDto SysLabelDto = new SysLabelDto();
        BeanUtils.copyProperties(SysLabelDto,this);
        return SysLabelDto;
    }

}
