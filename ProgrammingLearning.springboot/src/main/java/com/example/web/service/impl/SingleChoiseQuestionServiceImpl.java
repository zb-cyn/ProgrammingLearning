package com.example.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.web.SysConst;
import com.example.web.dto.*;
import com.example.web.dto.query.*;
import com.example.web.entity.*;
import com.example.web.mapper.*;
import com.example.web.enums.*;
import com.example.web.service.*;
import com.example.web.tools.dto.*;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;

import java.io.IOException;

import com.example.web.tools.*;

import java.text.DecimalFormat;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 单选题功能实现类
 */
@Service
public class SingleChoiseQuestionServiceImpl extends ServiceImpl<SingleChoiseQuestionMapper, SingleChoiseQuestion> implements SingleChoiseQuestionService {

    /**
     * 操作数据库AppUser表mapper对象
     */
    @Autowired
    private AppUserMapper AppUserMapper;
    /**
     * 操作数据库的SingleChoiseQuestion表mapper对象
     */
    @Autowired
    private SingleChoiseQuestionMapper SingleChoiseQuestionMapper;

    /**
     * 操作数据库的MultipleChoiceQuestion表mapper对象
     */
    @Autowired
    private MultipleChoiceQuestionMapper MultipleChoiceQuestionMapper;

    /**
     * 操作数据库的JudgmentQuestion表mapper对象
     */
    @Autowired
    private JudgmentQuestionMapper JudgmentQuestionMapper;

    @Autowired
    private QuestionnaireDetMapper QuestionnaireDetMapper;


    /**
     * 构建表查询sql
     */
    private LambdaQueryWrapper<SingleChoiseQuestion> BuilderQuery(SingleChoiseQuestionPagedInput input) {
        //声明一个支持单选题查询的(拉姆达)表达式
        LambdaQueryWrapper<SingleChoiseQuestion> queryWrapper = Wrappers.<SingleChoiseQuestion>lambdaQuery()
                .eq(input.getId() != null && input.getId() != 0, SingleChoiseQuestion::getId, input.getId())
                .eq(input.getCreatorId() != null, SingleChoiseQuestion::getCreatorId, input.getCreatorId());
        //如果前端搜索传入查询条件则拼接查询条件
        if (Extension.isNotNullOrEmpty(input.getTitle())) {
            queryWrapper = queryWrapper.like(SingleChoiseQuestion::getTitle, input.getTitle());
        }
        if (Extension.isNotNullOrEmpty(input.getType())) {
            queryWrapper = queryWrapper.like(SingleChoiseQuestion::getType, input.getType());
        }
        if (Extension.isNotNullOrEmpty(input.getOptionA())) {
            queryWrapper = queryWrapper.like(SingleChoiseQuestion::getOptionA, input.getOptionA());
        }
        if (Extension.isNotNullOrEmpty(input.getOptionB())) {
            queryWrapper = queryWrapper.like(SingleChoiseQuestion::getOptionB, input.getOptionB());
        }
        if (Extension.isNotNullOrEmpty(input.getOptionC())) {
            queryWrapper = queryWrapper.like(SingleChoiseQuestion::getOptionC, input.getOptionC());
        }
        if (Extension.isNotNullOrEmpty(input.getOptionD())) {
            queryWrapper = queryWrapper.like(SingleChoiseQuestion::getOptionD, input.getOptionD());
        }
        if (Extension.isNotNullOrEmpty(input.getAnalyzeResult())) {
            queryWrapper = queryWrapper.like(SingleChoiseQuestion::getAnalyzeResult, input.getAnalyzeResult());
        }
        if (Extension.isNotNullOrEmpty(input.getRightAnswer())) {
            queryWrapper = queryWrapper.like(SingleChoiseQuestion::getRightAnswer, input.getRightAnswer());
        }
        return queryWrapper;
    }

    /**
     * 处理单选题对于的外键数据
     */
    private List<SingleChoiseQuestionDto> DispatchItem(List<SingleChoiseQuestionDto> items) throws InvocationTargetException, IllegalAccessException {

        for (SingleChoiseQuestionDto item : items) {
            //查询出关联的创建用户信息

            AppUser CreatorAppUserEntity = AppUserMapper.selectList(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getId, item.getCreatorId())).stream().findFirst().orElse(new AppUser());
            item.setCreatorAppUserDto(CreatorAppUserEntity.MapToDto());
        }
        return items;
    }

    /**
     * 单选题分页查询
     */
    @SneakyThrows
    @Override
    public PagedResult<SingleChoiseQuestionDto> List(SingleChoiseQuestionPagedInput input) {
        //构建where条件+排序
        LambdaQueryWrapper<SingleChoiseQuestion> queryWrapper = BuilderQuery(input);

        //按创建时间从大到小排序 最新的显示在最前面
        queryWrapper = queryWrapper.orderByDesc(SingleChoiseQuestion::getCreationTime);
        //构建一个分页查询的model
        Page<SingleChoiseQuestion> page = new Page<>(input.getPage(), input.getLimit());
        //从数据库进行分页查询获取单选题数据
        IPage<SingleChoiseQuestion> pageRecords = SingleChoiseQuestionMapper.selectPage(page, queryWrapper);
        //获取所有满足条件的数据行数
        Long totalCount = SingleChoiseQuestionMapper.selectCount(queryWrapper);
        //把SingleChoiseQuestion实体转换成SingleChoiseQuestion传输模型
        List<SingleChoiseQuestionDto> items = Extension.copyBeanList(pageRecords.getRecords(), SingleChoiseQuestionDto.class);
        //计算表的数据
        items = DispatchItem(items);


        //返回一个分页结构给前端
        return PagedResult.GetInstance(items, totalCount);

    }

    /**
     * 单个单选题查询
     */
    @SneakyThrows
    @Override
    public SingleChoiseQuestionDto Get(SingleChoiseQuestionPagedInput input) {
        if (input.getId() == null) {
            return new SingleChoiseQuestionDto();
        }
        PagedResult<SingleChoiseQuestionDto> pagedResult = List(input);
        return pagedResult.getItems().stream().findFirst().orElse(new SingleChoiseQuestionDto());
    }

    /**
     * 单选题创建或者修改
     */
    @SneakyThrows
    @Override
    public SingleChoiseQuestionDto CreateOrEdit(SingleChoiseQuestionDto input) {
        //声明一个单选题实体
        SingleChoiseQuestion SingleChoiseQuestion = input.MapToEntity();
        //调用数据库的增加或者修改方法
        saveOrUpdate(SingleChoiseQuestion);
        //把传输模型返回给前端
        return SingleChoiseQuestion.MapToDto();
    }

    /**
     * 单选题删除
     */
    @Override
    public void Delete(IdInput input) {
        SingleChoiseQuestion entity = SingleChoiseQuestionMapper.selectById(input.getId());
        Long selectedCount = QuestionnaireDetMapper.selectCount(Wrappers.<QuestionnaireDet>lambdaQuery()
                .eq(QuestionnaireDet::getQuestionId, input.getId())
                .eq(QuestionnaireDet::getQuestionType, QuestionTypeEnum.单选题.index()));
        if (selectedCount > 0) {
            throw new RuntimeException("该题型已被使用无法删除");
        }
        SingleChoiseQuestionMapper.deleteById(entity);
    }

    /**
     * 单选题批量删除
     */
    @Override
    public void BatchDelete(IdsInput input) {
        for (Integer id : input.getIds()) {
            IdInput idInput = new IdInput();
            idInput.setId(id);
            Delete(idInput);
        }
    }
}
