package com.example.web.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.web.dto.AskedQuestionsDto;
import com.example.web.dto.query.AskedQuestionsPagedInput;
import com.example.web.entity.AskedQuestions;
import com.example.web.tools.dto.IdInput;
import com.example.web.tools.dto.IdsInput;
import com.example.web.tools.dto.PagedResult;

/**
 * 常见问题手册功能的Service接口的定义清单
 */
public interface AskedQuestionsService extends IService<AskedQuestions> {

    /**
     * 常见问题手册的分页查询方法接口定义
     */
    public PagedResult<AskedQuestionsDto> List(AskedQuestionsPagedInput input);

    /**
     * 常见问题手册的新增或者修改方法接口定义
     */
    public AskedQuestionsDto CreateOrEdit(AskedQuestionsDto input);

    /**
     * 获取常见问题手册信息
     */
    public AskedQuestionsDto Get(AskedQuestionsPagedInput input);

    /**
     * 常见问题手册删除
     */
    public void Delete(IdInput input);

    /**
     * 常见问题手册批量删除
     */
    public void BatchDelete(IdsInput input);

    /**
     * 得到列表里面的分类并且去重
     */
    public List<String> GetTypeList();

    /**
     * 有用反馈
     */
    public void AvailCountFeedback(AskedQuestionsDto input);

    /**
     * 无效反馈
     */
    public void NoAvailCountFeedback(AskedQuestionsDto input);
}
