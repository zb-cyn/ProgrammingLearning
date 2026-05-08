package com.example.web.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.web.dto.QuestionnaireDetDto;
import com.example.web.dto.query.QuestionnaireDetPagedInput;
import com.example.web.entity.QuestionnaireDet;
import com.example.web.tools.dto.IdInput;
import com.example.web.tools.dto.IdsInput;
import com.example.web.tools.dto.PagedResult;

/**
 * 问卷明细功能的Service接口的定义清单
 */
public interface QuestionnaireDetService extends IService<QuestionnaireDet> {

    /**
     * 问卷明细的分页查询方法接口定义
     */
    public PagedResult<QuestionnaireDetDto> List(QuestionnaireDetPagedInput input);

    /**
     * 问卷明细的新增或者修改方法接口定义
     */
    public QuestionnaireDetDto CreateOrEdit(QuestionnaireDetDto input);

    /**
     * 获取问卷明细信息
     */
    public QuestionnaireDetDto Get(QuestionnaireDetPagedInput input);

    /**
     * 问卷明细删除
     */
    public void Delete(IdInput input);

    /**
     * 问卷明细批量删除
     */
    public void BatchDelete(IdsInput input);

    /**
     * 批量创建
     */
    public void BatchCreate(List<QuestionnaireDetDto> input);
}
