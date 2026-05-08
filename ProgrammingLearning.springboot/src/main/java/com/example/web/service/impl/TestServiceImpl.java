package com.example.web.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.web.dto.AppUserDto;
import com.example.web.dto.QuestionnaireDto;
import com.example.web.dto.TestDto;
import com.example.web.dto.query.TestPagedInput;
import com.example.web.entity.AppUser;
import com.example.web.entity.Questionnaire;
import com.example.web.entity.QuestionnaireDet;
import com.example.web.entity.Test;
import com.example.web.entity.UserAnswer;
import com.example.web.mapper.AppUserMapper;
import com.example.web.mapper.QuestionnaireDetMapper;
import com.example.web.mapper.QuestionnaireMapper;
import com.example.web.mapper.TestMapper;
import com.example.web.mapper.UserAnswerMapper;
import com.example.web.service.TestService;
import com.example.web.tools.BaseContext;
import com.example.web.tools.Extension;
import com.example.web.tools.dto.IdInput;
import com.example.web.tools.dto.IdsInput;
import com.example.web.tools.dto.PagedResult;
import com.example.web.tools.exception.CustomException;

import lombok.SneakyThrows;

/**
 * 考试功能实现类
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

    /**
     * 操作数据库AppUser表mapper对象
     */
    @Autowired
    private AppUserMapper AppUserMapper;
    /**
     * 操作数据库的Test表mapper对象
     */
    @Autowired
    private TestMapper TestMapper;

    @Autowired
    private QuestionnaireMapper QuestionnaireMapper;

    @Autowired
    private UserAnswerMapper UserAnswerMapper;

    @Autowired
    private QuestionnaireDetMapper QuestionnaireDetMapper;

    /**
     * 构建表查询sql
     */
    private LambdaQueryWrapper<Test> BuilderQuery(TestPagedInput input) {
        // 声明一个支持考试查询的(拉姆达)表达式
        LambdaQueryWrapper<Test> queryWrapper = Wrappers.<Test>lambdaQuery()
                .eq(input.getId() != null && input.getId() != 0, Test::getId, input.getId())
                .eq(input.getCreatorId() != null, Test::getCreatorId, input.getCreatorId());
        // 如果前端搜索传入查询条件则拼接查询条件
        if (Extension.isNotNullOrEmpty(input.getTitle())) {
            queryWrapper = queryWrapper.like(Test::getTitle, input.getTitle());
        }

        if (input.getQuestionnaireId() != null) {
            queryWrapper = queryWrapper.eq(Test::getQuestionnaireId, input.getQuestionnaireId());
        }
        if (input.getActiveTimeRange() != null && !input.getActiveTimeRange().isEmpty()) {
            queryWrapper = queryWrapper.ge(Test::getBeginActiveTime, input.getActiveTimeRange().get(0));
            queryWrapper = queryWrapper.le(Test::getEndActiveTime, input.getActiveTimeRange().get(1));
        }
        if (input.getIsRadom() != null) {
            queryWrapper = queryWrapper.eq(Test::getIsRadom, input.getIsRadom());
        }
        if (input.getIsMy() != null) {
            Integer userId = BaseContext.getCurrentUserDto().getUserId();

        }
        return queryWrapper;
    }

    /**
     * 处理考试对于的外键数据
     */
    private List<TestDto> DispatchItem(List<TestDto> items) throws InvocationTargetException, IllegalAccessException {

        for (TestDto item : items) {

            // 查询创建人信息
            AppUser appUser = AppUserMapper.selectById(item.getCreatorId());
            item.setCreatorAppUserDto(appUser != null ? appUser.MapToDto() : new AppUserDto());

            // 查询出关联的Questionnaire表信息
            Questionnaire QuestionnaireEntity = QuestionnaireMapper.selectById(item.getQuestionnaireId());
            item.setQuestionnaireDto(
                    QuestionnaireEntity != null ? QuestionnaireEntity.MapToDto() : new QuestionnaireDto());

            Integer userId = BaseContext.getCurrentUserDto().getUserId();
            if (userId != null && userId != 0) {
                UserAnswer userAnswer = UserAnswerMapper.selectOne(
                        Wrappers.<UserAnswer>lambdaQuery()
                                .eq(UserAnswer::getTestId, item.getId())
                                .eq(UserAnswer::getQuestionnaireId, item.getQuestionnaireId())
                                .eq(UserAnswer::getUserId, userId)
                                .orderByDesc(UserAnswer::getCreationTime)
                                .last("limit 1"));

                item.setIsFinish(userAnswer != null);
            } else {
                item.setIsFinish(false);
            }

        }

        return items;
    }

    /**
     * 考试分页查询
     */
    @SneakyThrows
    @Override
    public PagedResult<TestDto> List(TestPagedInput input) {
        // 构建where条件+排序
        LambdaQueryWrapper<Test> queryWrapper = BuilderQuery(input);

        // 按创建时间从大到小排序 最新的显示在最前面
        queryWrapper = queryWrapper.orderByDesc(Test::getCreationTime);
        // 构建一个分页查询的model
        Page<Test> page = new Page<>(input.getPage(), input.getLimit());
        // 从数据库进行分页查询获取考试数据
        IPage<Test> pageRecords = TestMapper.selectPage(page, queryWrapper);
        // 获取所有满足条件的数据行数
        Long totalCount = TestMapper.selectCount(queryWrapper);
        // 把Test实体转换成Test传输模型
        List<TestDto> items = Extension.copyBeanList(pageRecords.getRecords(), TestDto.class);

        DispatchItem(items);
        // 返回一个分页结构给前端
        return PagedResult.GetInstance(items, totalCount);

    }

    /**
     * 单个考试查询
     */
    @SneakyThrows
    @Override
    public TestDto Get(TestPagedInput input) {
        if (input.getId() == null) {
            return new TestDto();
        }

        PagedResult<TestDto> pagedResult = List(input);
        return pagedResult.getItems().stream().findFirst().orElse(new TestDto());
    }

    /**
     * 考试创建或者修改
     */
    @SneakyThrows
    @Override
    public TestDto CreateOrEdit(TestDto input) {

        List<QuestionnaireDet> questionnaireDets = QuestionnaireDetMapper.selectList(
                Wrappers.<QuestionnaireDet>lambdaQuery()
                        .isNull(QuestionnaireDet::getScore)
                        .eq(QuestionnaireDet::getQuestionnaireId, input.getQuestionnaireId()));
        if (questionnaireDets.size() > 0) {
            throw new CustomException("试卷的题目分数未设置,请设置后在进行发布");
        }
        // 声明一个考试实体
        Test Test = input.MapToEntity();
        // 调用数据库的增加或者修改方法
        saveOrUpdate(Test);
        // 把传输模型返回给前端
        return Test.MapToDto();
    }

    /**
     * 考试删除
     */
    @Override
    public void Delete(IdInput input) {
        Test entity = TestMapper.selectById(input.getId());
        TestMapper.deleteById(entity);
    }

    /**
     * 考试批量删除
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
