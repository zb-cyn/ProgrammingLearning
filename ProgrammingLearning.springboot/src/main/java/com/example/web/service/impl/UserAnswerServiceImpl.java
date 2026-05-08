package com.example.web.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

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
import com.example.web.dto.UserAnswerDetDto;
import com.example.web.dto.UserAnswerDto;
import com.example.web.dto.UserAnswerLogDto;
import com.example.web.dto.query.UserAnswerPagedInput;
import com.example.web.entity.AppUser;
import com.example.web.entity.Questionnaire;
import com.example.web.entity.QuestionnaireDet;
import com.example.web.entity.Test;
import com.example.web.entity.UserAnswer;
import com.example.web.entity.UserAnswerDet;
import com.example.web.entity.UserAnswerLog;
import com.example.web.enums.QuestionTypeEnum;
import com.example.web.mapper.AppUserMapper;
import com.example.web.mapper.JudgmentQuestionMapper;
import com.example.web.mapper.MultipleChoiceQuestionMapper;
import com.example.web.mapper.QuestionnaireDetMapper;
import com.example.web.mapper.QuestionnaireMapper;
import com.example.web.mapper.SingleChoiseQuestionMapper;
import com.example.web.mapper.SubjectiveQuestionMapper;
import com.example.web.mapper.TestMapper;
import com.example.web.mapper.UserAnswerDetMapper;
import com.example.web.mapper.UserAnswerLogMapper;
import com.example.web.mapper.UserAnswerMapper;
import com.example.web.service.UserAnswerService;
import com.example.web.tools.Extension;
import com.example.web.tools.dto.IdInput;
import com.example.web.tools.dto.IdsInput;
import com.example.web.tools.dto.PagedResult;
import com.example.web.tools.exception.CustomException;

import lombok.SneakyThrows;

/**
 * 用户提交答题功能实现类
 */
@Service
public class UserAnswerServiceImpl extends ServiceImpl<UserAnswerMapper, UserAnswer> implements UserAnswerService {

    /**
     * 操作数据库AppUser表mapper对象
     */
    @Autowired
    private AppUserMapper AppUserMapper;
    /**
     * 操作数据库的UserAnswer表mapper对象
     */
    @Autowired
    private UserAnswerMapper UserAnswerMapper;

    @Autowired
    private UserAnswerDetMapper UserAnswerDetMapper;
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
    private SubjectiveQuestionMapper SubjectiveQuestionMapper;
    @Autowired
    private TestMapper TestMapper;

    @Autowired
    private UserAnswerLogMapper UserAnswerLogMapper;

    /**
     * 构建表查询sql
     */
    private LambdaQueryWrapper<UserAnswer> BuilderQuery(UserAnswerPagedInput input) {
        // 声明一个支持用户提交答题查询的(拉姆达)表达式
        LambdaQueryWrapper<UserAnswer> queryWrapper = Wrappers.<UserAnswer>lambdaQuery()
                .eq(input.getId() != null && input.getId() != 0, UserAnswer::getId, input.getId())
                .eq(input.getCreatorId() != null, UserAnswer::getCreatorId, input.getCreatorId());
        // 如果前端搜索传入查询条件则拼接查询条件

        if (input.getUserId() != null) {
            queryWrapper = queryWrapper.eq(UserAnswer::getUserId, input.getUserId());
        }

        if (input.getQuestionnaireId() != null) {
            queryWrapper = queryWrapper.eq(UserAnswer::getQuestionnaireId, input.getQuestionnaireId());
        }
        if (input.getSubmitTimeRange() != null && !input.getSubmitTimeRange().isEmpty()) {
            queryWrapper = queryWrapper.lt(UserAnswer::getSubmitTime, input.getSubmitTimeRange().get(1));
            queryWrapper = queryWrapper.gt(UserAnswer::getSubmitTime, input.getSubmitTimeRange().get(0));
        }
        if (input.getBeginTimeRange() != null && !input.getBeginTimeRange().isEmpty()) {
            queryWrapper = queryWrapper.lt(UserAnswer::getBeginTime, input.getBeginTimeRange().get(1));
            queryWrapper = queryWrapper.gt(UserAnswer::getBeginTime, input.getBeginTimeRange().get(0));
        }

        return queryWrapper;
    }

    /**
     * 处理用户提交答题对于的外键数据
     */
    private List<UserAnswerDto> DispatchItem(List<UserAnswerDto> items)
            throws InvocationTargetException, IllegalAccessException {

        for (UserAnswerDto item : items) {
            // 查询出关联的创建用户信息

            AppUser CreatorAppUserEntity = AppUserMapper
                    .selectList(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getId, item.getCreatorId())).stream()
                    .findFirst().orElse(new AppUser());
            item.setCreatorAppUserDto(CreatorAppUserEntity.MapToDto());

            // 查询出关联的AppUser表信息
            AppUser UserEntity = AppUserMapper
                    .selectList(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getId, item.getUserId())).stream()
                    .findFirst().orElse(new AppUser());
            item.setUserDto(UserEntity.MapToDto());

            // 查询测试
            Test test = TestMapper.selectById(item.getTestId());
            item.setTestDto(test.MapToDto());

            // 考试日志
            List<UserAnswerLog> userAnswerLogs = UserAnswerLogMapper.selectList(
                    Wrappers.<UserAnswerLog>lambdaQuery().eq(UserAnswerLog::getUserId, item.getUserId())
                            .eq(UserAnswerLog::getTestId, item.getTestId())
                            .orderByAsc(UserAnswerLog::getCreationTime));
            item.setUserAnswerLogDtoList(Extension.copyBeanList(userAnswerLogs, UserAnswerLogDto.class));

            // 查询出关联的Questionnaire表信息
            Questionnaire QuestionnaireEntity = QuestionnaireMapper
                    .selectList(
                            Wrappers.<Questionnaire>lambdaQuery().eq(Questionnaire::getId, item.getQuestionnaireId()))
                    .stream().findFirst().orElse(new Questionnaire());
            item.setQuestionnaireDto(QuestionnaireEntity.MapToDto());

            List<UserAnswerDet> userAnswerDets = UserAnswerDetMapper
                    .selectList(Wrappers.<UserAnswerDet>lambdaQuery().eq(UserAnswerDet::getUserAnswerId, item.getId())
                            .orderByAsc(UserAnswerDet::getSort));
            List<UserAnswerDetDto> userAnswerDetDtos = Extension.copyBeanList(userAnswerDets, UserAnswerDetDto.class);
            item.setUserAnswerDetDtoList(userAnswerDetDtos);

            item.setSingleChoiseQuestionCount((int) userAnswerDets.stream()
                    .filter(userAnswerDet -> userAnswerDet.getQuestionType() == QuestionTypeEnum.单选题.index()).count());
            item.setMultipleChoiceQuestionCount((int) userAnswerDets.stream()
                    .filter(userAnswerDet -> userAnswerDet.getQuestionType() == QuestionTypeEnum.多选题.index()).count());
            item.setJudgmentQuestionCount((int) userAnswerDets.stream()
                    .filter(userAnswerDet -> userAnswerDet.getQuestionType() == QuestionTypeEnum.判断题.index()).count());
            item.setSubjectiveQuestionCount((int) userAnswerDets.stream()
                    .filter(userAnswerDet -> userAnswerDet.getQuestionType() == QuestionTypeEnum.主观题.index()).count());

            item.setSingleChoiseQuestionTotalScore(userAnswerDets.stream()
                    .filter(userAnswerDet -> userAnswerDet.getQuestionType() == QuestionTypeEnum.单选题.index()
                            && userAnswerDet.getTotalScore() != null)
                    .mapToDouble(UserAnswerDet::getTotalScore).sum());

            item.setMultipleChoiceQuestionTotalScore(userAnswerDets.stream()
                    .filter(userAnswerDet -> userAnswerDet.getQuestionType() == QuestionTypeEnum.多选题.index()
                            && userAnswerDet.getTotalScore() != null)
                    .mapToDouble(UserAnswerDet::getTotalScore).sum());

            item.setJudgmentQuestionTotalScore(userAnswerDets.stream()
                    .filter(userAnswerDet -> userAnswerDet.getQuestionType() == QuestionTypeEnum.判断题.index()
                            && userAnswerDet.getTotalScore() != null)
                    .mapToDouble(UserAnswerDet::getTotalScore).sum());

            item.setSubjectiveQuestionTotalScore(userAnswerDets.stream()
                    .filter(userAnswerDet -> userAnswerDet.getQuestionType() == QuestionTypeEnum.主观题.index())
                    .mapToDouble(UserAnswerDet::getTotalScore).sum());

            item.setSingleChoiseQuestionGetScore(userAnswerDets.stream()
                    .filter(userAnswerDet -> userAnswerDet.getQuestionType() == QuestionTypeEnum.单选题.index()
                            && userAnswerDet.getGetScore() != null)
                    .mapToDouble(UserAnswerDet::getGetScore).sum());

            item.setMultipleChoiceQuestionGetScore(userAnswerDets.stream()
                    .filter(userAnswerDet -> userAnswerDet.getQuestionType() == QuestionTypeEnum.多选题.index()
                            && userAnswerDet.getGetScore() != null)
                    .mapToDouble(UserAnswerDet::getGetScore).sum());

            item.setJudgmentQuestionGetScore(userAnswerDets.stream()
                    .filter(userAnswerDet -> userAnswerDet.getQuestionType() == QuestionTypeEnum.判断题.index()
                            && userAnswerDet.getGetScore() != null)
                    .mapToDouble(UserAnswerDet::getGetScore).sum());

            item.setSubjectiveQuestionGetScore(userAnswerDets.stream()
                    .filter(userAnswerDet -> userAnswerDet.getQuestionType() == QuestionTypeEnum.主观题.index()
                            && userAnswerDet.getGetScore() != null)
                    .mapToDouble(UserAnswerDet::getGetScore).sum());

            for (UserAnswerDetDto userAnswerDetDto : item.getUserAnswerDetDtoList()) {
                if (userAnswerDetDto.getQuestionType() == QuestionTypeEnum.单选题.index()) {
                    SingleChoiseQuestionDto singleChoiseQuestionDto = SingleChoiseQuestionMapper
                            .selectById(userAnswerDetDto.getQuestionId()).MapToDto();
                    userAnswerDetDto.setSingleChoiseQuestionDto(singleChoiseQuestionDto);
                }

                if (userAnswerDetDto.getQuestionType() == QuestionTypeEnum.多选题.index()) {
                    MultipleChoiceQuestionDto multipleChoiceQuestionDto = MultipleChoiceQuestionMapper
                            .selectById(userAnswerDetDto.getQuestionId()).MapToDto();
                    userAnswerDetDto.setMultipleChoiceQuestionDto(multipleChoiceQuestionDto);
                }

                if (userAnswerDetDto.getQuestionType() == QuestionTypeEnum.判断题.index()) {
                    JudgmentQuestionDto judgmentQuestionDto = JudgmentQuestionMapper
                            .selectById(userAnswerDetDto.getQuestionId()).MapToDto();
                    userAnswerDetDto.setJudgmentQuestionDto(judgmentQuestionDto);
                }

                if (userAnswerDetDto.getQuestionType() == QuestionTypeEnum.主观题.index()) {
                    SubjectiveQuestionDto subjectiveQuestionDto = SubjectiveQuestionMapper
                            .selectById(userAnswerDetDto.getQuestionId()).MapToDto();
                    userAnswerDetDto.setSubjectiveQuestionDto(subjectiveQuestionDto);
                }

            }

        }
        return items;
    }

    /**
     * 用户提交答题分页查询
     */
    @SneakyThrows
    @Override
    public PagedResult<UserAnswerDto> List(UserAnswerPagedInput input) {
        // 构建where条件+排序
        LambdaQueryWrapper<UserAnswer> queryWrapper = BuilderQuery(input);

        // 按创建时间从大到小排序 最新的显示在最前面
        queryWrapper = queryWrapper.orderByDesc(UserAnswer::getCreationTime);
        // 构建一个分页查询的model
        Page<UserAnswer> page = new Page<>(input.getPage(), input.getLimit());
        // 从数据库进行分页查询获取用户提交答题数据
        IPage<UserAnswer> pageRecords = UserAnswerMapper.selectPage(page, queryWrapper);
        // 获取所有满足条件的数据行数
        Long totalCount = UserAnswerMapper.selectCount(queryWrapper);
        // 把UserAnswer实体转换成UserAnswer传输模型
        List<UserAnswerDto> items = Extension.copyBeanList(pageRecords.getRecords(), UserAnswerDto.class);
        // 计算表的数据
        items = DispatchItem(items);

        // 返回一个分页结构给前端
        return PagedResult.GetInstance(items, totalCount);

    }

    /**
     * 单个用户提交答题查询
     */
    @SneakyThrows
    @Override
    public UserAnswerDto Get(UserAnswerPagedInput input) {
        if (input.getId() == null) {
            return new UserAnswerDto();
        }
        PagedResult<UserAnswerDto> pagedResult = List(input);
        return pagedResult.getItems().stream().findFirst().orElse(new UserAnswerDto());
    }

    /**
     * 检查是否存在上一次没有完成的
     */
    @SneakyThrows
    @Override
    public UserAnswerDto CheckIsExist(UserAnswerPagedInput input) {

        // 查询测试
        Test test = TestMapper.selectById(input.getTestId());
        // 如果不在测试时间内
        if (test.getBeginActiveTime().isAfter(LocalDateTime.now())) {
            throw new CustomException("测试还未开始");
        }
        if (test.getEndActiveTime().isBefore(LocalDateTime.now())) {
            throw new CustomException("测试已结束");
        }

        UserAnswer userAnswer = UserAnswerMapper.selectOne(
                Wrappers.<UserAnswer>lambdaQuery()
                        .eq(UserAnswer::getTestId, input.getTestId())
                        .eq(UserAnswer::getQuestionnaireId, input.getQuestionnaireId())
                        .eq(UserAnswer::getUserId, input.getUserId())
                        .isNull(UserAnswer::getSubmitTime)
                        .orderByDesc(UserAnswer::getCreationTime)
                        .last("limit 1"));

        if (userAnswer != null) {
            return userAnswer.MapToDto();

        } else {
            return new UserAnswerDto();
        }
    }

    /**
     * 开始答题
     */
    @Override
    public UserAnswerDto BeginAnswer(UserAnswerDto input) throws InvocationTargetException, IllegalAccessException {

        List<UserAnswer> userAnswers = UserAnswerMapper.selectList(
                Wrappers.<UserAnswer>lambdaQuery().eq(UserAnswer::getQuestionnaireId, input.getQuestionnaireId())
                        .eq(UserAnswer::getUserId, input.getUserId())
                        .isNull(UserAnswer::getSubmitTime)
                        .eq(UserAnswer::getTestId, input.getTestId()));

        if (userAnswers.size() == 0) {

            // 查询测试
            Test test = TestMapper.selectById(input.getTestId());

            UserAnswer userAnswer = new UserAnswer();
            userAnswer.setUserId(input.getUserId());
            userAnswer.setTestId(input.getTestId());
            userAnswer.setBeginTime(LocalDateTime.now());

            userAnswer.setQuestionnaireId(input.getQuestionnaireId());
            UserAnswerMapper.insert(userAnswer);

            input.setId(userAnswer.getId());

            // 查询所有的题目
            QuestionnaireDto questionnaireDto = GetQuestionnaireDto(input.getQuestionnaireId());

            // 打乱顺序
            if (test.getIsRadom()) {
                Collections.shuffle(questionnaireDto.getQuestionnaireDetDtos());
            }
            int order = 1;
            for (QuestionnaireDetDto questionnaireDetDto : questionnaireDto.getQuestionnaireDetDtos()) {

                UserAnswerDet userAnswerDet = new UserAnswerDet();
                userAnswerDet.setQuestionType(questionnaireDetDto.getQuestionType());
                userAnswerDet.setUserAnswerId(input.getId());
                userAnswerDet.setSort(order);

                userAnswerDet.setQuestionnaireId(input.getQuestionnaireId());
                userAnswerDet.setQuestionId(questionnaireDetDto.getQuestionId());
                if (questionnaireDetDto.getQuestionType() == QuestionTypeEnum.单选题.index()) {
                    userAnswerDet.setRightAnswer(questionnaireDetDto.getSingleChoiseQuestionDto().getRightAnswer());
                    userAnswerDet.setTotalScore(questionnaireDetDto.getScore());
                }

                if (questionnaireDetDto.getQuestionType() == QuestionTypeEnum.多选题.index()) {
                    userAnswerDet.setRightAnswer(questionnaireDetDto.getMultipleChoiceQuestionDto().getRightAnswer());
                    userAnswerDet.setTotalScore(questionnaireDetDto.getScore());
                }

                if (questionnaireDetDto.getQuestionType() == QuestionTypeEnum.判断题.index()) {
                    userAnswerDet.setRightAnswer(questionnaireDetDto.getJudgmentQuestionDto().getRightAnswer());
                    userAnswerDet.setTotalScore(questionnaireDetDto.getScore());
                }

                if (questionnaireDetDto.getQuestionType() == QuestionTypeEnum.主观题.index()) {
                    userAnswerDet.setRightAnswer(questionnaireDetDto.getSubjectiveQuestionDto().getRightAnswer());
                    userAnswerDet.setTotalScore(questionnaireDetDto.getScore());
                }

                order++;
                UserAnswerDetMapper.insert(userAnswerDet);
            }

            UserAnswerLog userAnswerLog = new UserAnswerLog();

            userAnswerLog.setUserId(input.getUserId());

            userAnswerLog.setTestId(input.getTestId());
            userAnswerLog.setContent("用户正式开始答题");
            UserAnswerLogMapper.insert(userAnswerLog);
            return userAnswer.MapToDto();
        } else {
            return userAnswers.get(0).MapToDto();
        }

    }

    /**
     * 提交
     */
    @Override
    public void Sumbit(UserAnswerDto input) {
        UserAnswer userAnswer = UserAnswerMapper.selectById(input.getId());
        userAnswer.setSubmitTime(LocalDateTime.now());

        // 判断是否存在主观题
        List<UserAnswerDet> userAnswerDets = UserAnswerDetMapper.selectList(
                Wrappers.<UserAnswerDet>lambdaQuery().eq(UserAnswerDet::getUserAnswerId, input.getId()));
        // 如果存在没有答题的情况
        if (userAnswerDets.stream().anyMatch(userAnswerDet -> userAnswerDet.getGetScore() == null)) {
            throw new CustomException("存在未答题的题目");
        }

        // 如果不存在主观题
        if (!userAnswerDets.stream()
                .anyMatch(userAnswerDet -> userAnswerDet.getQuestionType() == QuestionTypeEnum.主观题.index())) {
            userAnswer.setIsMarking(true);

            // 计算总分
            userAnswer.setTotalScore(
                    userAnswerDets.stream().mapToDouble(UserAnswerDet::getGetScore).sum());
        }

        saveOrUpdate(userAnswer);

    }

    /**
     * 完成阅卷
     */
    @SneakyThrows
    @Override
    public void FinishMarking(UserAnswerDto input) {
        UserAnswer userAnswer = UserAnswerMapper.selectById(input.getId());
        userAnswer.setIsMarking(true);
        // 查询数据库
        List<UserAnswerDet> userAnswerDets = UserAnswerDetMapper.selectList(
                Wrappers.<UserAnswerDet>lambdaQuery().eq(UserAnswerDet::getUserAnswerId, input.getId()));
        // 是否存在为空得分的题目
        if (userAnswerDets.stream().anyMatch(userAnswerDet -> userAnswerDet.getGetScore() == null)) {
            throw new CustomException("存在未阅卷的题目");
        }
        // 计算总分
        userAnswer.setTotalScore(userAnswerDets.stream().mapToDouble(UserAnswerDet::getGetScore).sum());
        saveOrUpdate(userAnswer);
    }

    /**
     * 处理问卷对于的外键数据
     */
    private QuestionnaireDto GetQuestionnaireDto(Integer questionnaireId)
            throws InvocationTargetException, IllegalAccessException {

        Questionnaire questionnaire = QuestionnaireMapper.selectById(questionnaireId);
        QuestionnaireDto questionnaireDto = questionnaire.MapToDto();

        List<QuestionnaireDet> questionnaireDets = QuestionnaireDetMapper.selectList(
                Wrappers.<QuestionnaireDet>lambdaQuery().eq(QuestionnaireDet::getQuestionnaireId, questionnaireId)
                        .orderByAsc(QuestionnaireDet::getSort));

        questionnaireDto.setQuestionnaireDetDtos(Extension.copyBeanList(questionnaireDets, QuestionnaireDetDto.class));

        for (QuestionnaireDetDto questionnaireDetDto : questionnaireDto.getQuestionnaireDetDtos()) {
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

        return questionnaireDto;
    }

    /**
     * 用户提交答题创建或者修改
     */
    @SneakyThrows
    @Override
    public UserAnswerDto CreateOrEdit(UserAnswerDto input) {
        // 声明一个用户提交答题实体
        UserAnswer UserAnswer = input.MapToEntity();
        // 调用数据库的增加或者修改方法
        saveOrUpdate(UserAnswer);
        // 把传输模型返回给前端
        return UserAnswer.MapToDto();
    }

    /**
     * 用户提交答题删除
     */
    @Override
    public void Delete(IdInput input) {
        UserAnswer entity = UserAnswerMapper.selectById(input.getId());
        UserAnswerMapper.deleteById(entity);
    }

    /**
     * 用户提交答题批量删除
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
     * 自动交卷
     */
    @Override
    public void AutoSubmit() {
        // 查询所有没有提交的
        List<UserAnswer> userAnswers = UserAnswerMapper
                .selectList(Wrappers.<UserAnswer>lambdaQuery().eq(UserAnswer::getSubmitTime, null));
        // 逾期
        for (UserAnswer userAnswer : userAnswers) {
            // 查询试卷
            Questionnaire questionnaire = QuestionnaireMapper.selectById(userAnswer.getQuestionnaireId());
            if (userAnswer.getCreationTime().plusMinutes(questionnaire.getLimitMinute())
                    .isBefore(LocalDateTime.now())) {
                userAnswer.setSubmitTime(LocalDateTime.now());
                UserAnswerMapper.updateById(userAnswer);
            }
        }
    }
}
