package com.example.web.dto;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.example.web.entity.SingleChoiseQuestion;
import com.example.web.tools.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 单选题类
 */
@Data
public class SingleChoiseQuestionDto extends BaseDto {

    /**
     * 题干
     */
    @JsonProperty("Title")
    private String Title;

    /**
     * 分类
     */
    @JsonProperty("Type")
    private String Type;

    @JsonProperty("TypeList")
    private List<String> TypeList;

    /**
     * 选项A
     */
    @JsonProperty("OptionA")
    private String OptionA;

    /**
     * 选项B
     */
    @JsonProperty("OptionB")
    private String OptionB;

    /**
     * 选项C
     */
    @JsonProperty("OptionC")
    private String OptionC;

    /**
     * 选项D
     */
    @JsonProperty("OptionD")
    private String OptionD;

    /**
     * 解析
     */
    @JsonProperty("AnalyzeResult")
    private String AnalyzeResult;

    /**
     * 正确答案
     */
    @JsonProperty("RightAnswer")
    private String RightAnswer;

    /**
     * 把单选题传输模型转换成单选题实体
     */
    public SingleChoiseQuestion MapToEntity() throws InvocationTargetException, IllegalAccessException {
        SingleChoiseQuestion SingleChoiseQuestion = new SingleChoiseQuestion();
        BeanUtils.copyProperties(SingleChoiseQuestion, this);
        return SingleChoiseQuestion;
    }

}
