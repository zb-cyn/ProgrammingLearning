package com.example.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.web.dto.QuestionnaireDetDto;
import com.example.web.dto.query.QuestionnaireDetPagedInput;
import com.example.web.mapper.QuestionnaireDetMapper;
import com.example.web.service.QuestionnaireDetService;
import com.example.web.tools.dto.IdInput;
import com.example.web.tools.dto.IdsInput;
import com.example.web.tools.dto.PagedResult;

import lombok.SneakyThrows;

/**
 * 问卷明细控制器
 */
@RestController()
@RequestMapping("/QuestionnaireDet")
public class QuestionnaireDetController {
    @Autowired()
    private QuestionnaireDetService QuestionnaireDetService;
    @Autowired()
    private QuestionnaireDetMapper QuestionnaireDetMapper;

    /**
     * 问卷明细分页查询
     */
    @RequestMapping(value = "/List", method = RequestMethod.POST)
    @SneakyThrows
    public PagedResult<QuestionnaireDetDto> List(@RequestBody QuestionnaireDetPagedInput input) {
        return QuestionnaireDetService.List(input);
    }

    /**
     * 单个问卷明细查询接口
     */
    @RequestMapping(value = "/Get", method = RequestMethod.POST)
    @SneakyThrows
    public QuestionnaireDetDto Get(@RequestBody QuestionnaireDetPagedInput input) {

        return QuestionnaireDetService.Get(input);
    }

    /**
     * 问卷明细创建或则修改
     */
    @RequestMapping(value = "/CreateOrEdit", method = RequestMethod.POST)
    public QuestionnaireDetDto CreateOrEdit(@RequestBody QuestionnaireDetDto input) throws Exception {
        return QuestionnaireDetService.CreateOrEdit(input);
    }

    /**
     * 问卷明细删除
     */
    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    public void Delete(@RequestBody IdInput input) {
        QuestionnaireDetService.Delete(input);
    }

    /**
     * 问卷明细批量删除
     */
    @RequestMapping(value = "/BatchDelete", method = RequestMethod.POST)
    public void BatchDelete(@RequestBody IdsInput input) {
        QuestionnaireDetService.BatchDelete(input);
    }

    /**
     * 批量创建
     */
    @RequestMapping(value = "/BatchCreate", method = RequestMethod.POST)
    public void BatchCreate(@RequestBody List<QuestionnaireDetDto> input) {
        QuestionnaireDetService.BatchCreate(input);
    }

}
