package com.example.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.web.dto.AskedQuestionsDto;
import com.example.web.dto.query.AskedQuestionsPagedInput;
import com.example.web.mapper.AskedQuestionsMapper;
import com.example.web.service.AskedQuestionsService;
import com.example.web.tools.dto.IdInput;
import com.example.web.tools.dto.IdsInput;
import com.example.web.tools.dto.PagedResult;

import lombok.SneakyThrows;

/**
 * 常见问题手册控制器
 */
@RestController()
@RequestMapping("/AskedQuestions")
public class AskedQuestionsController {
    @Autowired()
    private AskedQuestionsService AskedQuestionsService;
    @Autowired()
    private AskedQuestionsMapper AskedQuestionsMapper;

    /**
     * 常见问题手册分页查询
     */
    @RequestMapping(value = "/List", method = RequestMethod.POST)
    @SneakyThrows
    public PagedResult<AskedQuestionsDto> List(@RequestBody AskedQuestionsPagedInput input) {
        return AskedQuestionsService.List(input);
    }

    /**
     * 单个常见问题手册查询接口
     */
    @RequestMapping(value = "/Get", method = RequestMethod.POST)
    @SneakyThrows
    public AskedQuestionsDto Get(@RequestBody AskedQuestionsPagedInput input) {

        return AskedQuestionsService.Get(input);
    }

    /**
     * 常见问题手册创建或则修改
     */
    @RequestMapping(value = "/CreateOrEdit", method = RequestMethod.POST)
    public AskedQuestionsDto CreateOrEdit(@RequestBody AskedQuestionsDto input) throws Exception {
        return AskedQuestionsService.CreateOrEdit(input);
    }

    /**
     * 常见问题手册删除
     */
    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    public void Delete(@RequestBody IdInput input) {
        AskedQuestionsService.Delete(input);
    }

    /**
     * 常见问题手册批量删除
     */
    @RequestMapping(value = "/BatchDelete", method = RequestMethod.POST)
    public void BatchDelete(@RequestBody IdsInput input) {
        AskedQuestionsService.BatchDelete(input);
    }

    /**
     * 得到列表里面的分类并且去重
     */
    @RequestMapping(value = "/GetTypeList", method = RequestMethod.POST)
    public List<String> GetTypeList() {
        return AskedQuestionsService.GetTypeList();
    }

    /**
     * 有用反馈
     */
    @RequestMapping(value = "/AvailCountFeedback", method = RequestMethod.POST)
    public void AvailCountFeedback(@RequestBody AskedQuestionsDto input) {
        AskedQuestionsService.AvailCountFeedback(input);
    }

    /**
     * 无效反馈
     */
    @RequestMapping(value = "/NoAvailCountFeedback", method = RequestMethod.POST)
    public void NoAvailCountFeedback(@RequestBody AskedQuestionsDto input) {
        AskedQuestionsService.NoAvailCountFeedback(input);
    }
}
