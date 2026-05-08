package com.example.web.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.web.dto.JudgmentQuestionDto;
import com.example.web.dto.MultipleChoiceQuestionDto;
import com.example.web.dto.QuestionnaireDetDto;
import com.example.web.dto.QuestionnaireDto;
import com.example.web.dto.SingleChoiseQuestionDto;
import com.example.web.dto.SubjectiveQuestionDto;
import com.example.web.dto.query.QuestionnairePagedInput;
import com.example.web.entity.AppUser;
import com.example.web.entity.JudgmentQuestion;
import com.example.web.entity.MultipleChoiceQuestion;
import com.example.web.entity.Questionnaire;
import com.example.web.entity.QuestionnaireDet;
import com.example.web.entity.SingleChoiseQuestion;
import com.example.web.entity.SubjectiveQuestion;
import com.example.web.entity.UserAnswerDet;
import com.example.web.enums.QuestionLevelEnum;
import com.example.web.enums.QuestionTypeEnum;
import com.example.web.mapper.AppUserMapper;
import com.example.web.mapper.JudgmentQuestionMapper;
import com.example.web.mapper.MultipleChoiceQuestionMapper;
import com.example.web.mapper.QuestionnaireDetMapper;
import com.example.web.mapper.QuestionnaireMapper;
import com.example.web.mapper.SingleChoiseQuestionMapper;
import com.example.web.mapper.SubjectiveQuestionMapper;
import com.example.web.mapper.UserAnswerDetMapper;
import com.example.web.mapper.UserAnswerMapper;
import com.example.web.service.QuestionnaireService;
import com.example.web.tools.Extension;
import com.example.web.tools.QuestionExtension;
import com.example.web.tools.dto.IdInput;
import com.example.web.tools.dto.IdsInput;
import com.example.web.tools.dto.PagedResult;
import com.example.web.tools.exception.CustomException;

import lombok.SneakyThrows;

/**
 * 问卷功能实现类
 */
@Service
public class QuestionnaireServiceImpl extends ServiceImpl<QuestionnaireMapper, Questionnaire>
        implements QuestionnaireService {

    /**
     * 操作数据库AppUser表mapper对象
     */
    @Autowired
    private AppUserMapper AppUserMapper;
    /**
     * 操作数据库的Questionnaire表mapper对象
     */
    @Autowired
    private QuestionnaireMapper QuestionnaireMapper;

    @Autowired
    private QuestionnaireDetMapper QuestionnaireDetMapper;
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
    private UserAnswerMapper UserAnswerMapper;

    @Autowired
    private UserAnswerDetMapper UserAnswerDetMapper;

    @Autowired
    private SubjectiveQuestionMapper SubjectiveQuestionMapper;

    /**
     * 构建表查询sql
     */
    private LambdaQueryWrapper<Questionnaire> BuilderQuery(QuestionnairePagedInput input) {
        // 声明一个支持问卷查询的(拉姆达)表达式
        LambdaQueryWrapper<Questionnaire> queryWrapper = Wrappers.<Questionnaire>lambdaQuery()
                .eq(input.getId() != null && input.getId() != 0, Questionnaire::getId, input.getId())
                .eq(input.getCreatorId() != null, Questionnaire::getCreatorId, input.getCreatorId());
        // 如果前端搜索传入查询条件则拼接查询条件
        if (Extension.isNotNullOrEmpty(input.getTitle())) {
            queryWrapper = queryWrapper.like(Questionnaire::getTitle, input.getTitle());
        }
        if (Extension.isNotNullOrEmpty(input.getSmallTitle())) {
            queryWrapper = queryWrapper.like(Questionnaire::getSmallTitle, input.getSmallTitle());
        }
        if (Extension.isNotNullOrEmpty(input.getType())) {
            queryWrapper = queryWrapper.like(Questionnaire::getType, input.getType());
        }

        if (input.getPublishUserId() != null) {
            queryWrapper = queryWrapper.eq(Questionnaire::getPublishUserId, input.getPublishUserId());
        }
        if (input.getIsTimeLimitRange() != null && !input.getIsTimeLimitRange().isEmpty()) {
            queryWrapper = queryWrapper.lt(Questionnaire::getIsTimeLimit, input.getIsTimeLimitRange().get(1));
            queryWrapper = queryWrapper.gt(Questionnaire::getIsTimeLimit, input.getIsTimeLimitRange().get(0));
        }
        return queryWrapper;
    }

    /**
     * 处理问卷对于的外键数据
     */
    private List<QuestionnaireDto> DispatchItem(List<QuestionnaireDto> items)
            throws InvocationTargetException, IllegalAccessException {

        for (QuestionnaireDto item : items) {
            // 查询出关联的创建用户信息

            AppUser CreatorAppUserEntity = AppUserMapper
                    .selectList(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getId, item.getCreatorId())).stream()
                    .findFirst().orElse(new AppUser());
            item.setCreatorAppUserDto(CreatorAppUserEntity.MapToDto());

            // 查询出关联的AppUser表信息
            AppUser PublishUserEntity = AppUserMapper
                    .selectList(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getId, item.getPublishUserId())).stream()
                    .findFirst().orElse(new AppUser());
            item.setPublishUserDto(PublishUserEntity.MapToDto());

            List<QuestionnaireDet> questionnaireDets = QuestionnaireDetMapper.selectList(
                    Wrappers.<QuestionnaireDet>lambdaQuery().eq(QuestionnaireDet::getQuestionnaireId, item.getId()));

            item.setSingleChoiseQuestionIds(
                    questionnaireDets.stream()
                            .filter(x -> x.getQuestionType() == QuestionTypeEnum.单选题.index())
                            .map(x -> x.getQuestionId())
                            .collect(Collectors.toList()));

            item.setMultipleChoiceQuestionIds(
                    questionnaireDets.stream()
                            .filter(x -> x.getQuestionType() == QuestionTypeEnum.多选题.index())
                            .map(x -> x.getQuestionId())
                            .collect(Collectors.toList()));

            item.setJudgmentQuestionIds(
                    questionnaireDets.stream()
                            .filter(x -> x.getQuestionType() == QuestionTypeEnum.判断题.index())
                            .map(x -> x.getQuestionId())
                            .collect(Collectors.toList()));

            item.setSubjectiveQuestionIds(
                    questionnaireDets.stream()
                            .filter(x -> x.getQuestionType() == QuestionTypeEnum.主观题.index())
                            .map(x -> x.getQuestionId())
                            .collect(Collectors.toList()));

            item.setQuestionnaireDetDtos(Extension.copyBeanList(questionnaireDets, QuestionnaireDetDto.class));

            for (QuestionnaireDetDto questionnaireDetDto : item.getQuestionnaireDetDtos()) {
                if (questionnaireDetDto.getQuestionType() == QuestionTypeEnum.单选题.index()) {
                    SingleChoiseQuestionDto singleChoiseQuestionDto = SingleChoiseQuestionMapper
                            .selectById(questionnaireDetDto.getQuestionId()).MapToDto();
                    questionnaireDetDto.setSingleChoiseQuestionDto(singleChoiseQuestionDto);
                }

                if (questionnaireDetDto.getQuestionType() == QuestionTypeEnum.多选题.index()) {
                    MultipleChoiceQuestionDto multipleChoiceQuestionDto = MultipleChoiceQuestionMapper
                            .selectById(questionnaireDetDto.getQuestionId()).MapToDto();
                    questionnaireDetDto.setMultipleChoiceQuestionDto(multipleChoiceQuestionDto);
                }

                if (questionnaireDetDto.getQuestionType() == QuestionTypeEnum.判断题.index()) {
                    JudgmentQuestionDto judgmentQuestionDto = JudgmentQuestionMapper
                            .selectById(questionnaireDetDto.getQuestionId()).MapToDto();
                    questionnaireDetDto.setJudgmentQuestionDto(judgmentQuestionDto);
                }

                if (questionnaireDetDto.getQuestionType() == QuestionTypeEnum.主观题.index()) {
                    SubjectiveQuestionDto subjectiveQuestionDto = SubjectiveQuestionMapper
                            .selectById(questionnaireDetDto.getQuestionId()).MapToDto();
                    questionnaireDetDto.setSubjectiveQuestionDto(subjectiveQuestionDto);
                }

            }

        }
        return items;
    }

    /**
     * 问卷分页查询
     */
    @SneakyThrows
    @Override
    public PagedResult<QuestionnaireDto> List(QuestionnairePagedInput input) {
        // 构建where条件+排序
        LambdaQueryWrapper<Questionnaire> queryWrapper = BuilderQuery(input);

        // 按创建时间从大到小排序 最新的显示在最前面
        queryWrapper = queryWrapper.orderByDesc(Questionnaire::getCreationTime);
        // 构建一个分页查询的model
        Page<Questionnaire> page = new Page<>(input.getPage(), input.getLimit());
        // 从数据库进行分页查询获取问卷数据
        IPage<Questionnaire> pageRecords = QuestionnaireMapper.selectPage(page, queryWrapper);
        // 获取所有满足条件的数据行数
        Long totalCount = QuestionnaireMapper.selectCount(queryWrapper);
        // 把Questionnaire实体转换成Questionnaire传输模型
        List<QuestionnaireDto> items = Extension.copyBeanList(pageRecords.getRecords(), QuestionnaireDto.class);
        // 计算表的数据
        items = DispatchItem(items);

        // 返回一个分页结构给前端
        return PagedResult.GetInstance(items, totalCount);

    }

    /**
     * 单个问卷查询
     */
    @SneakyThrows
    @Override
    public QuestionnaireDto Get(QuestionnairePagedInput input) {
        if (input.getId() == null) {
            return new QuestionnaireDto();
        }
        PagedResult<QuestionnaireDto> pagedResult = List(input);
        return pagedResult.getItems().stream().findFirst().orElse(new QuestionnaireDto());
    }

    /**
     * 问卷创建或者修改
     */
    @SneakyThrows
    @Override
    public QuestionnaireDto CreateOrEdit(QuestionnaireDto input) {
        // 直接删除所有的选项
        QuestionnaireDetMapper.delete(
                Wrappers.<QuestionnaireDet>lambdaQuery().eq(QuestionnaireDet::getQuestionnaireId, input.getId()));

        // 声明一个问卷实体
        Questionnaire Questionnaire = input.MapToEntity();
        // 调用数据库的增加或者修改方法
        saveOrUpdate(Questionnaire);

        // 插入单选题
        for (Integer SingleChoiseQuestionId : input.getSingleChoiseQuestionIds()) {
            QuestionnaireDet questionnaireDet = new QuestionnaireDet();
            questionnaireDet.setQuestionnaireId(Questionnaire.getId());
            questionnaireDet.setQuestionType(QuestionTypeEnum.单选题.index());
            questionnaireDet.setQuestionId(SingleChoiseQuestionId);
            QuestionnaireDetMapper.insert(questionnaireDet);
        }
        // 插入多选题
        for (Integer MultipleChoiceQuestionId : input.getMultipleChoiceQuestionIds()) {
            QuestionnaireDet questionnaireDet = new QuestionnaireDet();
            questionnaireDet.setQuestionnaireId(Questionnaire.getId());
            questionnaireDet.setQuestionType(QuestionTypeEnum.多选题.index());
            questionnaireDet.setQuestionId(MultipleChoiceQuestionId);
            QuestionnaireDetMapper.insert(questionnaireDet);
        }
        // 插入判断题
        for (Integer JudgmentQuestionId : input.getJudgmentQuestionIds()) {
            QuestionnaireDet questionnaireDet = new QuestionnaireDet();
            questionnaireDet.setQuestionnaireId(Questionnaire.getId());
            questionnaireDet.setQuestionType(QuestionTypeEnum.判断题.index());
            questionnaireDet.setQuestionId(JudgmentQuestionId);
            QuestionnaireDetMapper.insert(questionnaireDet);
        }

        // 把传输模型返回给前端
        return Questionnaire.MapToDto();
    }

    /**
     * 问卷删除
     */
    @Override
    public void Delete(IdInput input) {
        Questionnaire entity = QuestionnaireMapper.selectById(input.getId());
        QuestionnaireDetMapper.delete(
                Wrappers.<QuestionnaireDet>lambdaQuery().eq(QuestionnaireDet::getQuestionnaireId, input.getId()));
        QuestionnaireMapper.deleteById(entity);

    }

    /**
     * 问卷批量删除
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
     * 问卷选项各个占比
     */
    @Override
    public List<Object> QuestionOptionsDataCollect(QuestionnairePagedInput input) {

        ArrayList<Object> arrayList = new ArrayList<>();

        List<Questionnaire> questionnaires = QuestionnaireMapper.selectList(
                Wrappers.<Questionnaire>lambdaQuery().eq(input.getId() != null, Questionnaire::getId, input.getId()));

        // 循环问卷
        for (Questionnaire questionnaire : questionnaires) {

            HashMap<String, Object> questionnaireHasMap = new HashMap<>();

            questionnaireHasMap.put("questionnaireName", questionnaire.getTitle());
            questionnaireHasMap.put("questionnaireId", questionnaire.getId());

            ArrayList<Object> questionnaireList = new ArrayList<>();

            // 查询所有选项
            List<QuestionnaireDet> questionnaireDets = QuestionnaireDetMapper.selectList(Wrappers
                    .<QuestionnaireDet>lambdaQuery().eq(QuestionnaireDet::getQuestionnaireId, questionnaire.getId()));
            for (QuestionnaireDet questionnaireDet : questionnaireDets) {
                HashMap<String, Object> stringObjectHashMap = new HashMap<>();

                List<UserAnswerDet> userAnswerDets = UserAnswerDetMapper
                        .selectList(Wrappers.<UserAnswerDet>lambdaQuery()
                                .isNotNull(UserAnswerDet::getUserAnswer)
                                .eq(UserAnswerDet::getQuestionType, questionnaireDet.getQuestionType())
                                .eq(UserAnswerDet::getQuestionId, questionnaireDet.getQuestionId()));

                if (questionnaireDet.getQuestionType() == QuestionTypeEnum.单选题.index()) {
                    SingleChoiseQuestion singleChoiseQuestion = SingleChoiseQuestionMapper
                            .selectById(questionnaireDet.getQuestionId());

                    stringObjectHashMap.put("questionId", singleChoiseQuestion.getId());
                    stringObjectHashMap.put("questionType", QuestionTypeEnum.单选题.index());
                    stringObjectHashMap.put("questionName", singleChoiseQuestion.getTitle());

                    long optionASize = userAnswerDets.stream().filter(x -> "A".equals(x.getUserAnswer())).count();
                    stringObjectHashMap.put("optionA", optionASize);

                    long optionBSize = userAnswerDets.stream().filter(x -> "B".equals(x.getUserAnswer())).count();
                    stringObjectHashMap.put("optionB", optionBSize);

                    long optionCSize = userAnswerDets.stream().filter(x -> "C".equals(x.getUserAnswer())).count();
                    stringObjectHashMap.put("optionC", optionCSize);

                    long optionDSize = userAnswerDets.stream().filter(x -> "D".equals(x.getUserAnswer())).count();
                    stringObjectHashMap.put("optionD", optionDSize);

                }

                if (questionnaireDet.getQuestionType() == QuestionTypeEnum.多选题.index()) {
                    MultipleChoiceQuestion multipleChoiceQuestion = MultipleChoiceQuestionMapper
                            .selectById(questionnaireDet.getQuestionId());

                    stringObjectHashMap.put("questionId", multipleChoiceQuestion.getId());
                    stringObjectHashMap.put("questionType", QuestionTypeEnum.多选题.index());
                    stringObjectHashMap.put("questionName", multipleChoiceQuestion.getTitle());

                    long optionASize = userAnswerDets.stream().filter(x -> x.getUserAnswer().contains("A")).count();
                    stringObjectHashMap.put("optionA", optionASize);

                    long optionBSize = userAnswerDets.stream().filter(x -> x.getUserAnswer().contains("B")).count();
                    stringObjectHashMap.put("optionB", optionBSize);

                    long optionCSize = userAnswerDets.stream().filter(x -> x.getUserAnswer().contains("C")).count();
                    stringObjectHashMap.put("optionC", optionCSize);

                    long optionDSize = userAnswerDets.stream().filter(x -> x.getUserAnswer().contains("D")).count();
                    stringObjectHashMap.put("optionD", optionDSize);

                }

                if (questionnaireDet.getQuestionType() == QuestionTypeEnum.判断题.index()) {
                    JudgmentQuestion judgmentQuestion = JudgmentQuestionMapper
                            .selectById(questionnaireDet.getQuestionId());

                    stringObjectHashMap.put("questionId", judgmentQuestion.getId());
                    stringObjectHashMap.put("questionType", QuestionTypeEnum.判断题.index());
                    stringObjectHashMap.put("questionName", judgmentQuestion.getTitle());

                    long optionTrue = userAnswerDets.stream().filter(x -> "true".equals(x.getUserAnswer())).count();
                    stringObjectHashMap.put("optionTrue", optionTrue);

                    long optionFalse = userAnswerDets.stream().filter(x -> "false".equals(x.getUserAnswer())).count();
                    stringObjectHashMap.put("optionFalse", optionFalse);

                }

                // 计算正确率
                long rightCount = userAnswerDets.stream().filter(x -> Boolean.TRUE.equals(x.getIsRight())).count();
                if (rightCount > 0) {
                    stringObjectHashMap.put("rightCount",
                            Extension.ToFixed2(rightCount / (double) userAnswerDets.size() * 100));
                } else {
                    stringObjectHashMap.put("rightCount", 0);
                }

                questionnaireList.add(stringObjectHashMap);

            }
            questionnaireHasMap.put("options", questionnaireList);

            arrayList.add(questionnaireHasMap);
        }

        return arrayList;

    }

    /**
     * 自动组卷
     */
    @SneakyThrows
    @Override
    public QuestionnaireDto AutoCreate(QuestionnaireDto input) {
        List<String> questionTypes = QuestionExtension.StringToList(input.getType());
        QuestionLevelEnum difficulty = QuestionLevelEnum.GetEnum(input.getQuestionLevel()); // 新增:题目难度要求

        // 获取用户答题历史数据,用于分析正确率
        List<UserAnswerDet> userAnswerDets = UserAnswerDetMapper.selectList(
                Wrappers.<UserAnswerDet>lambdaQuery());
        Map<Integer, Double> questionAccuracyMap = calculateQuestionAccuracy(userAnswerDets);

        // 查询
        List<SingleChoiseQuestion> singleChoiseQuestions = SingleChoiseQuestionMapper
                .selectList(Wrappers.<SingleChoiseQuestion>lambdaQuery());
        // 转换
        List<SingleChoiseQuestionDto> singleChoiseQuestionDtos = Extension.copyBeanList(singleChoiseQuestions,
                SingleChoiseQuestionDto.class);
        // 循环
        for (SingleChoiseQuestionDto singleChoiseQuestionDto : singleChoiseQuestionDtos) {
            singleChoiseQuestionDto.setTypeList(QuestionExtension.StringToList(singleChoiseQuestionDto.getType()));
        }
        // 计算和类型有交集的题目
        List<SingleChoiseQuestionDto> sameSingleChoiseQuestionDtos = singleChoiseQuestionDtos.stream()
                .filter(x -> questionTypes.contains(x.getType())).collect(Collectors.toList());

        // 查询多选题
        List<MultipleChoiceQuestion> multipleChoiceQuestions = MultipleChoiceQuestionMapper
                .selectList(Wrappers.<MultipleChoiceQuestion>lambdaQuery());

        // 转换
        List<MultipleChoiceQuestionDto> multipleChoiceQuestionDtos = Extension.copyBeanList(multipleChoiceQuestions,
                MultipleChoiceQuestionDto.class);

        // 计算和类型有交集的题目
        List<MultipleChoiceQuestionDto> sameMultipleChoiceQuestionDtos = multipleChoiceQuestionDtos.stream()
                .filter(x -> questionTypes.contains(x.getType())).collect(Collectors.toList());

        List<JudgmentQuestion> judgmentQuestions = JudgmentQuestionMapper
                .selectList(Wrappers.<JudgmentQuestion>lambdaQuery());

        // 转换
        List<JudgmentQuestionDto> judgmentQuestionDtos = Extension.copyBeanList(judgmentQuestions,
                JudgmentQuestionDto.class);

        // 计算和类型有交集的题目
        List<JudgmentQuestionDto> sameJudgmentQuestionDtos = judgmentQuestionDtos.stream()
                .filter(x -> questionTypes.contains(x.getType())).collect(Collectors.toList());

        List<SubjectiveQuestion> subjectiveQuestions = SubjectiveQuestionMapper
                .selectList(Wrappers.<SubjectiveQuestion>lambdaQuery());

        // 转换
        List<SubjectiveQuestionDto> subjectiveQuestionDtos = Extension.copyBeanList(subjectiveQuestions,
                SubjectiveQuestionDto.class);

        // 计算和类型有交集的题目
        List<SubjectiveQuestionDto> sameSubjectiveQuestionDtos = subjectiveQuestionDtos.stream()
                .filter(x -> questionTypes.contains(x.getType())).collect(Collectors.toList());

        // 根据难度和正确率过滤题目
        sameSingleChoiseQuestionDtos = filterQuestionsByDifficulty(
                sameSingleChoiseQuestionDtos,
                questionAccuracyMap,
                difficulty);

        sameMultipleChoiceQuestionDtos = filterQuestionsByDifficulty(
                sameMultipleChoiceQuestionDtos,
                questionAccuracyMap,
                difficulty);

        // 如果过滤后题目数量不足,提高难度要求重新筛选
        if (isInsufficientQuestions(sameSingleChoiseQuestionDtos, input.getSingleChoiseQuestionCount())) {
            List<SingleChoiseQuestionDto> additionalQuestions = relaxDifficultyFilter(
                    singleChoiseQuestionDtos,
                    questionAccuracyMap,
                    difficulty);
            // 合并原有题目和新筛选的题目
            if (additionalQuestions.size() > 0) {
                sameSingleChoiseQuestionDtos.addAll(additionalQuestions);
            }
        }
        // 如果过滤后题目数量不足,提高难度要求重新筛选
        if (isInsufficientQuestions(sameMultipleChoiceQuestionDtos, input.getMultipleChoiceQuestionCount())) {
            List<MultipleChoiceQuestionDto> additionalQuestions = relaxDifficultyFilter(
                    multipleChoiceQuestionDtos,
                    questionAccuracyMap,
                    difficulty);
            // 合并原有题目和新筛选的题目
            if (additionalQuestions.size() > 0) {
                sameMultipleChoiceQuestionDtos.addAll(additionalQuestions);
            }
        }
        // 如果过滤后题目数量不足,提高难度要求重新筛选
        if (isInsufficientQuestions(sameJudgmentQuestionDtos, input.getJudgmentQuestionCount())) {
            List<JudgmentQuestionDto> additionalQuestions = relaxDifficultyFilter(
                    judgmentQuestionDtos,
                    questionAccuracyMap,
                    difficulty);
            // 合并原有题目和新筛选的题目
            if (additionalQuestions.size() > 0) {
                sameJudgmentQuestionDtos.addAll(additionalQuestions);
            }
        }
        // 如果过滤后题目数量不足,提高难度要求重新筛选
        if (isInsufficientQuestions(sameSubjectiveQuestionDtos, input.getSubjectiveQuestionCount())) {
            List<SubjectiveQuestionDto> additionalQuestions = relaxDifficultyFilter(
                    subjectiveQuestionDtos,
                    questionAccuracyMap,
                    difficulty);
            // 合并原有题目和新筛选的题目
            if (additionalQuestions.size() > 0) {
                sameSubjectiveQuestionDtos.addAll(additionalQuestions);
            }
        }

        // 随机选择指定数量的题目
        List<SingleChoiseQuestionDto> selectedSingleChoice = this.<SingleChoiseQuestionDto>getRandomQuestions(
                sameSingleChoiseQuestionDtos,
                input.getSingleChoiseQuestionCount() != null ? input.getSingleChoiseQuestionCount() : 0);

        List<MultipleChoiceQuestionDto> selectedMultipleChoice = this.<MultipleChoiceQuestionDto>getRandomQuestions(
                sameMultipleChoiceQuestionDtos,
                input.getMultipleChoiceQuestionCount() != null ? input.getMultipleChoiceQuestionCount() : 0);

        List<JudgmentQuestionDto> selectedJudgment = this.<JudgmentQuestionDto>getRandomQuestions(
                sameJudgmentQuestionDtos,
                input.getJudgmentQuestionCount() != null ? input.getJudgmentQuestionCount() : 0);

        List<SubjectiveQuestionDto> selectedSubjective = this.<SubjectiveQuestionDto>getRandomQuestions(
                sameSubjectiveQuestionDtos,
                input.getSubjectiveQuestionCount() != null ? input.getSubjectiveQuestionCount() : 0);

        // 设置选中的题目ID
        input.setSingleChoiseQuestionIds(selectedSingleChoice.stream()
                .map(x -> x.getId())
                .collect(Collectors.toList()));
        input.setMultipleChoiceQuestionIds(selectedMultipleChoice.stream()
                .map(x -> x.getId())
                .collect(Collectors.toList()));
        input.setJudgmentQuestionIds(selectedJudgment.stream()
                .map(x -> x.getId())
                .collect(Collectors.toList()));
        input.setSubjectiveQuestionIds(selectedSubjective.stream()
                .map(x -> x.getId())
                .collect(Collectors.toList()));

        // 如果一个题目都没则报错
        if (input.getSingleChoiseQuestionIds().isEmpty() && input.getMultipleChoiceQuestionIds().isEmpty()
                && input.getJudgmentQuestionIds().isEmpty() && input.getSubjectiveQuestionIds().isEmpty()) {
            throw new CustomException("没有题目可以组卷");
        }
        // 创建问卷
        return CreateOrEdit(input);
    }

    /**
     * 计算每道题的正确率
     */
    private Map<Integer, Double> calculateQuestionAccuracy(List<UserAnswerDet> userAnswerDets) {
        Map<Integer, Double> accuracyMap = new HashMap<>();

        // 按题目ID分组统计
        Map<Integer, List<UserAnswerDet>> questionGroups = userAnswerDets.stream()
                .collect(Collectors.groupingBy(UserAnswerDet::getQuestionId));

        questionGroups.forEach((questionId, answers) -> {
            long correctCount = answers.stream()
                    .filter(a -> Boolean.TRUE.equals(a.getIsRight()))
                    .count();
            double accuracy = answers.isEmpty() ? 0.5 : correctCount / (double) answers.size();
            accuracyMap.put(questionId, accuracy);
        });

        return accuracyMap;
    }

    /**
     * 根据难度过滤题目
     */
    private <T> List<T> filterQuestionsByDifficulty(List<T> questions,
            Map<Integer, Double> accuracyMap,
            QuestionLevelEnum difficulty) {
        double minAccuracy = 0;
        double maxAccuracy = 1;

        // 根据难度设置正确率范围
        switch (difficulty) {
            case 简单:
                minAccuracy = 0.7;
                break;
            case 中等:
                minAccuracy = 0.3;
                maxAccuracy = 0.7;
                break;
            case 困难:
                maxAccuracy = 0.3;
                break;
        }

        double finalMinAccuracy = minAccuracy;
        double finalMaxAccuracy = maxAccuracy;

        // 过滤题目
        return questions.stream()
                .filter(q -> {
                    Integer id = getQuestionId(q);
                    double accuracy = accuracyMap.getOrDefault(id, 0.5);
                    return accuracy >= finalMinAccuracy && accuracy <= finalMaxAccuracy;
                })
                .collect(Collectors.toList());
    }

    /**
     * 放宽难度过滤条件
     */
    private <T> List<T> relaxDifficultyFilter(List<T> questions,
            Map<Integer, Double> accuracyMap,
            QuestionLevelEnum difficulty) {
        // 修改难度扩大范围的逻辑
        switch (difficulty) {
            case 简单:
                // 简单题不够时,从中等题目中选取
                return filterQuestionsByDifficulty(questions, accuracyMap, QuestionLevelEnum.中等);
            case 中等:
                // 中等题不够时,从困难题目中选取
                return filterQuestionsByDifficulty(questions, accuracyMap, QuestionLevelEnum.困难);
            default:
                return questions; // 困难题已是最高难度,返回所有题目
        }
    }

    /**
     * 检查题目数量是否足够
     */
    private <T> boolean isInsufficientQuestions(List<T> questions, Integer required) {
        return required != null && questions.size() < required;
    }

    /**
     * 获取题目ID的工具方法
     */
    private Integer getQuestionId(Object question) {
        try {
            Method getIdMethod = question.getClass().getMethod("getId");
            return (Integer) getIdMethod.invoke(question);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 从列表中随机选择指定数量的题目
     */
    private <T> List<T> getRandomQuestions(List<T> questions, int count) {
        if (count <= 0 || questions.isEmpty()) {
            return new ArrayList<>();
        }

        // 如果要求的数量大于现有题目数量，返回所有题目
        if (count >= questions.size()) {
            return new ArrayList<>(questions);
        }

        // 随机选择题目
        List<T> shuffled = new ArrayList<>(questions);
        Collections.shuffle(shuffled);
        return shuffled.subList(0, count);
    }

}
