package com.example.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.web.dto.QuestionnaireDto;
import com.example.web.dto.query.QuestionnairePagedInput;
import com.example.web.mapper.QuestionnaireMapper;
import com.example.web.service.QuestionnaireService;
import com.example.web.tools.dto.IdInput;
import com.example.web.tools.dto.IdsInput;
import com.example.web.tools.dto.PagedResult;

import lombok.SneakyThrows;

/**
 * 问卷控制器
 */
@RestController()
@RequestMapping("/Questionnaire")
public class QuestionnaireController {
    @Autowired()
    private QuestionnaireService QuestionnaireService;
    @Autowired()
    private QuestionnaireMapper QuestionnaireMapper;

    /**
     * 问卷分页查询
     */
    @RequestMapping(value = "/List", method = RequestMethod.POST)
    @SneakyThrows
    public PagedResult<QuestionnaireDto> List(@RequestBody QuestionnairePagedInput input) {
        return QuestionnaireService.List(input);
    }

    /**
     * 单个问卷查询接口
     */
    @RequestMapping(value = "/Get", method = RequestMethod.POST)
    @SneakyThrows
    public QuestionnaireDto Get(@RequestBody QuestionnairePagedInput input) {

        return QuestionnaireService.Get(input);
    }

    /**
     * 问卷创建或则修改
     */
    @RequestMapping(value = "/CreateOrEdit", method = RequestMethod.POST)
    public QuestionnaireDto CreateOrEdit(@RequestBody QuestionnaireDto input) throws Exception {
        return QuestionnaireService.CreateOrEdit(input);
    }

    /**
     * 问卷删除
     */
    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    public void Delete(@RequestBody IdInput input) {
        QuestionnaireService.Delete(input);
    }

    /**
     * 问卷批量删除
     */
    @RequestMapping(value = "/BatchDelete", method = RequestMethod.POST)
    public void BatchDelete(@RequestBody IdsInput input) {
        QuestionnaireService.BatchDelete(input);
    }

    /**
     * 问卷选项各个占比
     */
    @RequestMapping(value = "/QuestionOptionsDataCollect", method = RequestMethod.POST)
    @SneakyThrows
    public List<Object> QuestionOptionsDataCollect(@RequestBody QuestionnairePagedInput input) {
        return QuestionnaireService.QuestionOptionsDataCollect(input);
    }

    /**
     * 自动组卷
     */
    @RequestMapping(value = "/AutoCreate", method = RequestMethod.POST)
    public QuestionnaireDto AutoCreate(@RequestBody QuestionnaireDto input) {
        return QuestionnaireService.AutoCreate(input);
    }
}
