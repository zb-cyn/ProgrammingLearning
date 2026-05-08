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
import com.example.web.dto.QuestionnaireDetDto;
import com.example.web.dto.query.QuestionnaireDetPagedInput;
import com.example.web.entity.AppUser;
import com.example.web.entity.JudgmentQuestion;
import com.example.web.entity.MultipleChoiceQuestion;
import com.example.web.entity.Questionnaire;
import com.example.web.entity.QuestionnaireDet;
import com.example.web.entity.SingleChoiseQuestion;
import com.example.web.entity.SubjectiveQuestion;
import com.example.web.enums.QuestionTypeEnum;
import com.example.web.mapper.AppUserMapper;
import com.example.web.mapper.JudgmentQuestionMapper;
import com.example.web.mapper.MultipleChoiceQuestionMapper;
import com.example.web.mapper.QuestionnaireDetMapper;
import com.example.web.mapper.QuestionnaireMapper;
import com.example.web.mapper.SingleChoiseQuestionMapper;
import com.example.web.mapper.SubjectiveQuestionMapper;
import com.example.web.service.QuestionnaireDetService;
import com.example.web.tools.Extension;
import com.example.web.tools.dto.IdInput;
import com.example.web.tools.dto.IdsInput;
import com.example.web.tools.dto.PagedResult;

import lombok.SneakyThrows;

/**
 * 问卷明细功能实现类
 */
@Service
public class QuestionnaireDetServiceImpl extends ServiceImpl<QuestionnaireDetMapper, QuestionnaireDet>
        implements QuestionnaireDetService {

    /**
     * 操作数据库AppUser表mapper对象
     */
    @Autowired
    private AppUserMapper AppUserMapper;
    /**
     * 操作数据库的QuestionnaireDet表mapper对象
     */
    @Autowired
    private QuestionnaireDetMapper QuestionnaireDetMapper;
    @Autowired
    private QuestionnaireMapper QuestionnaireMapper;

    /**
     * 操作数据库的SingleChoiseQuestion表mapper对象
     */
    @Autowired
    private SingleChoiseQuestionMapper SingleChoiseQuestionMapper;

    @Autowired
    private MultipleChoiceQuestionMapper MultipleChoiceQuestionMapper;

    @Autowired
    private JudgmentQuestionMapper JudgmentQuestionMapper;

    @Autowired
    private SubjectiveQuestionMapper SubjectiveQuestionMapper;

    private LambdaQueryWrapper<QuestionnaireDet> BuilderQuery(QuestionnaireDetPagedInput input) {
        // 声明一个支持问卷明细查询的(拉姆达)表达式
        LambdaQueryWrapper<QuestionnaireDet> queryWrapper = Wrappers.<QuestionnaireDet>lambdaQuery()
                .eq(input.getId() != null && input.getId() != 0, QuestionnaireDet::getId, input.getId())
                .eq(input.getCreatorId() != null, QuestionnaireDet::getCreatorId, input.getCreatorId());
        // 如果前端搜索传入查询条件则拼接查询条件

        if (input.getQuestionnaireId() != null) {
            queryWrapper = queryWrapper.eq(QuestionnaireDet::getQuestionnaireId, input.getQuestionnaireId());
        }

        if (input.getQuestionType() != null) {
            queryWrapper = queryWrapper.eq(QuestionnaireDet::getQuestionType, input.getQuestionType());
        }
        return queryWrapper;
    }

    /**
     * 处理问卷明细对于的外键数据
     */
    private List<QuestionnaireDetDto> DispatchItem(List<QuestionnaireDetDto> items)
            throws InvocationTargetException, IllegalAccessException {

        for (QuestionnaireDetDto item : items) {
            // 查询出关联的创建用户信息

            AppUser CreatorAppUserEntity = AppUserMapper
                    .selectList(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getId, item.getCreatorId())).stream()
                    .findFirst().orElse(new AppUser());
            item.setCreatorAppUserDto(CreatorAppUserEntity.MapToDto());

            // 查询出关联的Questionnaire表信息
            Questionnaire QuestionnaireEntity = QuestionnaireMapper
                    .selectList(
                            Wrappers.<Questionnaire>lambdaQuery().eq(Questionnaire::getId, item.getQuestionnaireId()))
                    .stream().findFirst().orElse(new Questionnaire());
            item.setQuestionnaireDto(QuestionnaireEntity.MapToDto());

            if (item.getQuestionType() == QuestionTypeEnum.单选题.index()) {
                SingleChoiseQuestion SingleChoiseQuestionEntity = SingleChoiseQuestionMapper
                        .selectList(Wrappers.<SingleChoiseQuestion>lambdaQuery().eq(SingleChoiseQuestion::getId,
                                item.getQuestionId()))
                        .stream().findFirst().orElse(new SingleChoiseQuestion());
                item.setSingleChoiseQuestionDto(SingleChoiseQuestionEntity.MapToDto());
            }
            if (item.getQuestionType() == QuestionTypeEnum.多选题.index()) {
                MultipleChoiceQuestion MultipleChoiceQuestionEntity = MultipleChoiceQuestionMapper
                        .selectList(Wrappers.<MultipleChoiceQuestion>lambdaQuery().eq(MultipleChoiceQuestion::getId,
                                item.getQuestionId()))
                        .stream().findFirst().orElse(new MultipleChoiceQuestion());
                item.setMultipleChoiceQuestionDto(MultipleChoiceQuestionEntity.MapToDto());
            }
            if (item.getQuestionType() == QuestionTypeEnum.判断题.index()) {
                JudgmentQuestion JudgmentQuestionEntity = JudgmentQuestionMapper
                        .selectList(Wrappers.<JudgmentQuestion>lambdaQuery().eq(JudgmentQuestion::getId,
                                item.getQuestionId()))
                        .stream().findFirst().orElse(new JudgmentQuestion());
                item.setJudgmentQuestionDto(JudgmentQuestionEntity.MapToDto());
            }
            if (item.getQuestionType() == QuestionTypeEnum.主观题.index()) {
                SubjectiveQuestion SubjectiveQuestionEntity = SubjectiveQuestionMapper
                        .selectList(Wrappers.<SubjectiveQuestion>lambdaQuery().eq(SubjectiveQuestion::getId,
                                item.getQuestionId()))
                        .stream().findFirst().orElse(new SubjectiveQuestion());
                item.setSubjectiveQuestionDto(SubjectiveQuestionEntity.MapToDto());
            }

        }
        return items;
    }

    /**
     * 问卷明细分页查询
     */
    @SneakyThrows
    @Override
    public PagedResult<QuestionnaireDetDto> List(QuestionnaireDetPagedInput input) {
        // 构建where条件+排序
        LambdaQueryWrapper<QuestionnaireDet> queryWrapper = BuilderQuery(input);

        // 按创建时间从大到小排序 最新的显示在最前面
        queryWrapper = queryWrapper.orderByAsc(QuestionnaireDet::getSort);
        // 构建一个分页查询的model
        Page<QuestionnaireDet> page = new Page<>(input.getPage(), input.getLimit());
        // 从数据库进行分页查询获取问卷明细数据
        IPage<QuestionnaireDet> pageRecords = QuestionnaireDetMapper.selectPage(page, queryWrapper);
        // 获取所有满足条件的数据行数
        Long totalCount = QuestionnaireDetMapper.selectCount(queryWrapper);
        // 把QuestionnaireDet实体转换成QuestionnaireDet传输模型
        List<QuestionnaireDetDto> items = Extension.copyBeanList(pageRecords.getRecords(), QuestionnaireDetDto.class);
        // 计算表的数据
        items = DispatchItem(items);

        // 返回一个分页结构给前端
        return PagedResult.GetInstance(items, totalCount);

    }

    /**
     * 单个问卷明细查询
     */
    @SneakyThrows
    @Override
    public QuestionnaireDetDto Get(QuestionnaireDetPagedInput input) {
        if (input.getId() == null) {
            return new QuestionnaireDetDto();
        }
        PagedResult<QuestionnaireDetDto> pagedResult = List(input);
        return pagedResult.getItems().stream().findFirst().orElse(new QuestionnaireDetDto());
    }

    /**
     * 问卷明细创建或者修改
     */
    @SneakyThrows
    @Override
    public QuestionnaireDetDto CreateOrEdit(QuestionnaireDetDto input) {
        // 声明一个问卷明细实体
        QuestionnaireDet QuestionnaireDet = input.MapToEntity();
        // 调用数据库的增加或者修改方法
        saveOrUpdate(QuestionnaireDet);
        // 把传输模型返回给前端
        return QuestionnaireDet.MapToDto();
    }

    /**
     * 问卷明细删除
     */
    @Override
    public void Delete(IdInput input) {
        QuestionnaireDet entity = QuestionnaireDetMapper.selectById(input.getId());
        QuestionnaireDetMapper.deleteById(entity);
    }

    /**
     * 问卷明细批量删除
     */
    @Override
    public void BatchDelete(IdsInput input) {
        for (Integer id : input.getIds()) {
            IdInput idInput = new IdInput();
            idInput.setId(id);
            Delete(idInput);
        }
    }

    /**
     * 批量创建
     */
    @SneakyThrows
    @Override
    public void BatchCreate(List<QuestionnaireDetDto> input) {
        // 得到1个的试卷id
        Integer questionnaireId = input.get(0).getQuestionnaireId();

        // 删除名下的所有题目
        QuestionnaireDetMapper.delete(Wrappers.<QuestionnaireDet>lambdaQuery().eq(QuestionnaireDet::getQuestionnaireId,
                questionnaireId));

        int i = 1;
        // 遍历问卷明细传输模型
        for (QuestionnaireDetDto item : input) {
            item.setSort(i);
            // 把问卷明细传输模型转换成问卷明细实体
            QuestionnaireDet QuestionnaireDet = item.MapToEntity();
            // 把试卷id赋值给问卷明细实体
            QuestionnaireDet.setQuestionnaireId(questionnaireId);
            // 把问卷明细实体插入到数据库
            QuestionnaireDetMapper.insert(QuestionnaireDet);
            i++;
        }
    }
}
