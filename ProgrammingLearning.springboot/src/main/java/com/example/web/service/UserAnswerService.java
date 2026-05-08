package com.example.web.service;

import java.lang.reflect.InvocationTargetException;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.web.dto.UserAnswerDto;
import com.example.web.dto.query.UserAnswerPagedInput;
import com.example.web.entity.UserAnswer;
import com.example.web.tools.dto.IdInput;
import com.example.web.tools.dto.IdsInput;
import com.example.web.tools.dto.PagedResult;

import lombok.SneakyThrows;

/**
 * 用户提交答题功能的Service接口的定义清单
 */
public interface UserAnswerService extends IService<UserAnswer> {

    /**
     * 用户提交答题的分页查询方法接口定义
     */
    public PagedResult<UserAnswerDto> List(UserAnswerPagedInput input);

    /**
     * 检查是否存在上一次没有完成的
     */
    @SneakyThrows
    UserAnswerDto CheckIsExist(UserAnswerPagedInput input);

    /**
     * 开始答题
     */
    UserAnswerDto BeginAnswer(UserAnswerDto input) throws InvocationTargetException, IllegalAccessException;

    /**
     * 提交答题
     */
    void Sumbit(UserAnswerDto input);

    /**
     * 用户提交答题的新增或者修改方法接口定义
     */
    public UserAnswerDto CreateOrEdit(UserAnswerDto input);

    /**
     * 获取用户提交答题信息
     */
    public UserAnswerDto Get(UserAnswerPagedInput input);

    /**
     * 用户提交答题删除
     */
    public void Delete(IdInput input);

    /**
     * 用户提交答题批量删除
     */
    public void BatchDelete(IdsInput input);

    /**
     * 完成阅卷
     */
    @SneakyThrows
    public void FinishMarking(UserAnswerDto input);

    /**
     * 自动交卷
     */
    public void AutoSubmit();
}
