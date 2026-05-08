package com.example.web.entity;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.web.dto.UserAnswerLogDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 考试日志表
 */
@Data
@TableName("`UserAnswerLog`")
public class UserAnswerLog extends BaseEntity {

    /**
     * 用户
     */
    @JsonProperty("UserId")
    @TableField(value = "UserId", updateStrategy = FieldStrategy.IGNORED)
    private Integer UserId;

    /**
     * 日志
     */
    @JsonProperty("Content")
    @TableField(value = "Content", updateStrategy = FieldStrategy.IGNORED)
    private String Content;

    /**
     * 测试
     */
    @JsonProperty("TestId")
    @TableField(value = "TestId", updateStrategy = FieldStrategy.IGNORED)
    private Integer TestId;

    /**
     * 把考试日志实体转换成考试日志传输模型
     */
    public UserAnswerLogDto MapToDto() throws InvocationTargetException, IllegalAccessException {
        UserAnswerLogDto UserAnswerLogDto = new UserAnswerLogDto();

        BeanUtils.copyProperties(UserAnswerLogDto, this);

        return UserAnswerLogDto;
    }

}
