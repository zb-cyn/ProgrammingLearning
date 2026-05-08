package com.example.web.dto;
import com.example.web.enums.*;
import com.example.web.tools.dto.BaseDto;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.sql.Date;
import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.example.web.entity.*;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
/**
 * 系统标签类
 */
@Data
public class SysLabelDto extends BaseDto
{



    /**
     * 标签名称
     */
    @JsonProperty("Name")
    private String Name;


    /**
     * 显示顺序
     */
    @JsonProperty("Sort")
    private String Sort;


    /**
     * 编码
     */
    @JsonProperty("Code")
    private String Code;

    /**
     * 把系统标签传输模型转换成系统标签实体
     */
    public SysLabel MapToEntity() throws InvocationTargetException, IllegalAccessException {
        SysLabel SysLabel= new SysLabel();
        BeanUtils.copyProperties(SysLabel,this);
        return SysLabel;
    }

}
