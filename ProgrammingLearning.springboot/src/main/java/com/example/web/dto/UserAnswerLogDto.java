package com.example.web.dto;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.example.web.entity.UserAnswerLog;
import com.example.web.tools.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 考试日志类
 */
@Data
public class UserAnswerLogDto extends BaseDto {

    /**
     * 用户
     */
    @JsonProperty("UserId")
    private Integer UserId;

    /**
     * 日志
     */
    @JsonProperty("Content")
    private String Content;

    /**
     * 测试
     */
    @JsonProperty("TestId")
    private Integer TestId;

    /**
     * 把考试日志传输模型转换成考试日志实体
     */
    public UserAnswerLog MapToEntity() throws InvocationTargetException, IllegalAccessException {
        UserAnswerLog UserAnswerLog = new UserAnswerLog();

        BeanUtils.copyProperties(UserAnswerLog, this);

        return UserAnswerLog;
    }

}
