package com.example.web.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.web.dto.QuestionnaireDto;
import com.example.web.dto.query.QuestionnairePagedInput;
import com.example.web.entity.Questionnaire;
import com.example.web.tools.dto.IdInput;
import com.example.web.tools.dto.IdsInput;
import com.example.web.tools.dto.PagedResult;

/**
 * 问卷功能的Service接口的定义清单
 */
public interface QuestionnaireService extends IService<Questionnaire> {

    /**
     * 问卷的分页查询方法接口定义
     */
    public PagedResult<QuestionnaireDto> List(QuestionnairePagedInput input);

    /**
     * 问卷的新增或者修改方法接口定义
     */
    public QuestionnaireDto CreateOrEdit(QuestionnaireDto input);

    /**
     * 获取问卷信息
     */
    public QuestionnaireDto Get(QuestionnairePagedInput input);

    /**
     * 问卷删除
     */
    public void Delete(IdInput input);

    /**
     * 问卷批量删除
     */
    public void BatchDelete(IdsInput input);

    /**
     * 问卷选项各个占比
     */

    List<Object> QuestionOptionsDataCollect(QuestionnairePagedInput input);

    /**
     * 自动组卷
     */
    public QuestionnaireDto AutoCreate(QuestionnaireDto input);
}
