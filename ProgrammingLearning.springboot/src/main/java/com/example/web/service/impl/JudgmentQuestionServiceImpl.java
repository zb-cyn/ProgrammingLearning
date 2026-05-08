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
 * 判断题型功能实现类
 */
@Service
public class JudgmentQuestionServiceImpl extends ServiceImpl<JudgmentQuestionMapper, JudgmentQuestion> implements JudgmentQuestionService {

    /**
     * 操作数据库AppUser表mapper对象
     */
    @Autowired
    private AppUserMapper AppUserMapper;
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
    private LambdaQueryWrapper<JudgmentQuestion> BuilderQuery(JudgmentQuestionPagedInput input) {
        //声明一个支持判断题型查询的(拉姆达)表达式
        LambdaQueryWrapper<JudgmentQuestion> queryWrapper = Wrappers.<JudgmentQuestion>lambdaQuery()
                .eq(input.getId() != null && input.getId() != 0, JudgmentQuestion::getId, input.getId())
                .eq(input.getCreatorId() != null, JudgmentQuestion::getCreatorId, input.getCreatorId());
        //如果前端搜索传入查询条件则拼接查询条件
        if (Extension.isNotNullOrEmpty(input.getTitle())) {
            queryWrapper = queryWrapper.like(JudgmentQuestion::getTitle, input.getTitle());
        }
        if (Extension.isNotNullOrEmpty(input.getRightAnswer())) {
            queryWrapper = queryWrapper.like(JudgmentQuestion::getRightAnswer, input.getRightAnswer());
        }
        if (Extension.isNotNullOrEmpty(input.getAnalyzeResult())) {
            queryWrapper = queryWrapper.like(JudgmentQuestion::getAnalyzeResult, input.getAnalyzeResult());
        }
        if (Extension.isNotNullOrEmpty(input.getType())) {
            queryWrapper = queryWrapper.like(JudgmentQuestion::getType, input.getType());
        }
        return queryWrapper;
    }

    /**
     * 处理判断题型对于的外键数据
     */
    private List<JudgmentQuestionDto> DispatchItem(List<JudgmentQuestionDto> items) throws InvocationTargetException, IllegalAccessException {

        for (JudgmentQuestionDto item : items) {
            //查询出关联的创建用户信息

            AppUser CreatorAppUserEntity = AppUserMapper.selectList(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getId, item.getCreatorId())).stream().findFirst().orElse(new AppUser());
            item.setCreatorAppUserDto(CreatorAppUserEntity.MapToDto());
        }
        return items;
    }

    /**
     * 判断题型分页查询
     */
    @SneakyThrows
    @Override
    public PagedResult<JudgmentQuestionDto> List(JudgmentQuestionPagedInput input) {
        //构建where条件+排序
        LambdaQueryWrapper<JudgmentQuestion> queryWrapper = BuilderQuery(input);

        //按创建时间从大到小排序 最新的显示在最前面
        queryWrapper = queryWrapper.orderByDesc(JudgmentQuestion::getCreationTime);
        //构建一个分页查询的model
        Page<JudgmentQuestion> page = new Page<>(input.getPage(), input.getLimit());
        //从数据库进行分页查询获取判断题型数据
        IPage<JudgmentQuestion> pageRecords = JudgmentQuestionMapper.selectPage(page, queryWrapper);
        //获取所有满足条件的数据行数
        Long totalCount = JudgmentQuestionMapper.selectCount(queryWrapper);
        //把JudgmentQuestion实体转换成JudgmentQuestion传输模型
        List<JudgmentQuestionDto> items = Extension.copyBeanList(pageRecords.getRecords(), JudgmentQuestionDto.class);
        //计算表的数据
        items = DispatchItem(items);


        //返回一个分页结构给前端
        return PagedResult.GetInstance(items, totalCount);

    }

    /**
     * 单个判断题型查询
     */
    @SneakyThrows
    @Override
    public JudgmentQuestionDto Get(JudgmentQuestionPagedInput input) {
        if (input.getId() == null) {
            return new JudgmentQuestionDto();
        }
        PagedResult<JudgmentQuestionDto> pagedResult = List(input);
        return pagedResult.getItems().stream().findFirst().orElse(new JudgmentQuestionDto());
    }

    /**
     * 判断题型创建或者修改
     */
    @SneakyThrows
    @Override
    public JudgmentQuestionDto CreateOrEdit(JudgmentQuestionDto input) {
        //声明一个判断题型实体
        JudgmentQuestion JudgmentQuestion = input.MapToEntity();
        //调用数据库的增加或者修改方法
        saveOrUpdate(JudgmentQuestion);
        //把传输模型返回给前端
        return JudgmentQuestion.MapToDto();
    }

    /**
     * 判断题型删除
     */
    @Override
    public void Delete(IdInput input) {
        JudgmentQuestion entity = JudgmentQuestionMapper.selectById(input.getId());

        Long selectedCount = QuestionnaireDetMapper.selectCount(Wrappers.<QuestionnaireDet>lambdaQuery()
                .eq(QuestionnaireDet::getQuestionId, input.getId())
                .eq(QuestionnaireDet::getQuestionType, QuestionTypeEnum.判断题.index()));
        if (selectedCount > 0) {
            throw new RuntimeException("该题型已被使用无法删除");
        }

        JudgmentQuestionMapper.deleteById(entity);
    }

    /**
     * 判断题型批量删除
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
