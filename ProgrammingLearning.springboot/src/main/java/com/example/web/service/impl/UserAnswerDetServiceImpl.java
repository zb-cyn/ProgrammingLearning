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
import com.example.web.dto.UserAnswerDetDto;
import com.example.web.dto.query.UserAnswerDetPagedInput;
import com.example.web.entity.AppUser;
import com.example.web.entity.JudgmentQuestion;
import com.example.web.entity.MultipleChoiceQuestion;
import com.example.web.entity.Questionnaire;
import com.example.web.entity.SingleChoiseQuestion;
import com.example.web.entity.SubjectiveQuestion;
import com.example.web.entity.UserAnswerDet;
import com.example.web.enums.QuestionTypeEnum;
import com.example.web.mapper.AppUserMapper;
import com.example.web.mapper.JudgmentQuestionMapper;
import com.example.web.mapper.MultipleChoiceQuestionMapper;
import com.example.web.mapper.QuestionnaireMapper;
import com.example.web.mapper.SingleChoiseQuestionMapper;
import com.example.web.mapper.SubjectiveQuestionMapper;
import com.example.web.mapper.UserAnswerDetMapper;
import com.example.web.service.UserAnswerDetService;
import com.example.web.tools.Extension;
import com.example.web.tools.QuestionExtension;
import com.example.web.tools.dto.IdInput;
import com.example.web.tools.dto.IdsInput;
import com.example.web.tools.dto.PagedResult;

import lombok.SneakyThrows;

/**
 * 用户提交答题明细功能实现类
 */
@Service
public class UserAnswerDetServiceImpl extends ServiceImpl<UserAnswerDetMapper, UserAnswerDet>
        implements UserAnswerDetService {

    /**
     * 操作数据库AppUser表mapper对象
     */
    @Autowired
    private AppUserMapper AppUserMapper;
    /**
     * 操作数据库的UserAnswerDet表mapper对象
     */
    @Autowired
    private UserAnswerDetMapper UserAnswerDetMapper;
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

    /**
     * 构建表查询sql
     */
    private LambdaQueryWrapper<UserAnswerDet> BuilderQuery(UserAnswerDetPagedInput input) {
        // 声明一个支持用户提交答题明细查询的(拉姆达)表达式
        LambdaQueryWrapper<UserAnswerDet> queryWrapper = Wrappers.<UserAnswerDet>lambdaQuery()
                .eq(input.getId() != null && input.getId() != 0, UserAnswerDet::getId, input.getId())
                .eq(input.getCreatorId() != null, UserAnswerDet::getCreatorId, input.getCreatorId());
        // 如果前端搜索传入查询条件则拼接查询条件
        if (Extension.isNotNullOrEmpty(input.getUserAnswer())) {
            queryWrapper = queryWrapper.like(UserAnswerDet::getUserAnswer, input.getUserAnswer());
        }
        if (Extension.isNotNullOrEmpty(input.getRightAnswer())) {
            queryWrapper = queryWrapper.like(UserAnswerDet::getRightAnswer, input.getRightAnswer());
        }

        if (input.getUserAnswerId() != null) {
            queryWrapper = queryWrapper.eq(UserAnswerDet::getUserAnswerId, input.getUserAnswerId());
        }

        if (input.getQuestionnaireId() != null) {
            queryWrapper = queryWrapper.eq(UserAnswerDet::getQuestionnaireId, input.getQuestionnaireId());
        }

        if (input.getQuestionType() != null) {
            queryWrapper = queryWrapper.eq(UserAnswerDet::getQuestionType, input.getQuestionType());
        }
        if (input.getIsRight() != null) {
            queryWrapper = queryWrapper.eq(UserAnswerDet::getIsRight, input.getIsRight());
        }
        return queryWrapper;
    }

    /**
     * 处理用户提交答题明细对于的外键数据
     */
    private List<UserAnswerDetDto> DispatchItem(List<UserAnswerDetDto> items)
            throws InvocationTargetException, IllegalAccessException {

        for (UserAnswerDetDto item : items) {
            // 查询出关联的创建用户信息

            AppUser CreatorAppUserEntity = AppUserMapper
                    .selectList(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getId, item.getCreatorId())).stream()
                    .findFirst().orElse(new AppUser());
            item.setCreatorAppUserDto(CreatorAppUserEntity.MapToDto());

            // 查询出关联的AppUser表信息
            AppUser UserAnswerEntity = AppUserMapper
                    .selectList(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getId, item.getUserAnswerId())).stream()
                    .findFirst().orElse(new AppUser());
            item.setUserAnswerDto(UserAnswerEntity.MapToDto());

            // 查询出关联的Questionnaire表信息
            Questionnaire QuestionnaireEntity = QuestionnaireMapper
                    .selectList(
                            Wrappers.<Questionnaire>lambdaQuery().eq(Questionnaire::getId, item.getQuestionnaireId()))
                    .stream().findFirst().orElse(new Questionnaire());
            item.setQuestionnaireDto(QuestionnaireEntity.MapToDto());
        }
        return items;
    }

    /**
     * 用户提交答题明细分页查询
     */
    @SneakyThrows
    @Override
    public PagedResult<UserAnswerDetDto> List(UserAnswerDetPagedInput input) {
        // 构建where条件+排序
        LambdaQueryWrapper<UserAnswerDet> queryWrapper = BuilderQuery(input);

        // 按创建时间从大到小排序 最新的显示在最前面
        queryWrapper = queryWrapper.orderByDesc(UserAnswerDet::getCreationTime);
        // 构建一个分页查询的model
        Page<UserAnswerDet> page = new Page<>(input.getPage(), input.getLimit());
        // 从数据库进行分页查询获取用户提交答题明细数据
        IPage<UserAnswerDet> pageRecords = UserAnswerDetMapper.selectPage(page, queryWrapper);
        // 获取所有满足条件的数据行数
        Long totalCount = UserAnswerDetMapper.selectCount(queryWrapper);
        // 把UserAnswerDet实体转换成UserAnswerDet传输模型
        List<UserAnswerDetDto> items = Extension.copyBeanList(pageRecords.getRecords(), UserAnswerDetDto.class);
        // 计算表的数据
        items = DispatchItem(items);

        // 返回一个分页结构给前端
        return PagedResult.GetInstance(items, totalCount);

    }

    /**
     * 单个用户提交答题明细查询
     */
    @SneakyThrows
    @Override
    public UserAnswerDetDto Get(UserAnswerDetPagedInput input) {
        if (input.getId() == null) {
            return new UserAnswerDetDto();
        }

        PagedResult<UserAnswerDetDto> pagedResult = List(input);
        return pagedResult.getItems().stream().findFirst().orElse(new UserAnswerDetDto());
    }

    /**
     * 用户提交答题明细创建或者修改
     */
    @SneakyThrows
    @Override
    public UserAnswerDetDto CreateOrEdit(UserAnswerDetDto input) {

        if (input.getQuestionType() == QuestionTypeEnum.单选题.index()) {
            SingleChoiseQuestion singleChoiseQuestion = SingleChoiseQuestionMapper.selectById(input.getQuestionId());

            input.setIsRight(input.getUserAnswer().equals(singleChoiseQuestion.getRightAnswer()));
            input.setGetScore(input.getIsRight() ? input.getTotalScore() : 0);
        } else if (input.getQuestionType() == QuestionTypeEnum.多选题.index()) {
            MultipleChoiceQuestion multipleChoiceQuestion = MultipleChoiceQuestionMapper
                    .selectById(input.getQuestionId());

            String rightAnswer = QuestionExtension.StringSort(multipleChoiceQuestion.getRightAnswer());

            String userAnswerResult = QuestionExtension.StringSort(input.getUserAnswer());

            input.setIsRight(userAnswerResult.equals(rightAnswer));

            input.setRightAnswer(rightAnswer);
            input.setGetScore(input.getIsRight() ? input.getTotalScore() : 0);

        } else if (input.getQuestionType() == QuestionTypeEnum.判断题.index()) {

            JudgmentQuestion judgmentQuestion = JudgmentQuestionMapper.selectById(input.getQuestionId());
            input.setIsRight(input.getUserAnswer().equals(judgmentQuestion.getRightAnswer()));
            input.setGetScore(input.getIsRight() ? input.getTotalScore() : 0);
        } else if (input.getQuestionType() == QuestionTypeEnum.主观题.index()) {
            SubjectiveQuestion subjectiveQuestion = SubjectiveQuestionMapper.selectById(input.getQuestionId());
            input.setIsRight(input.getTotalScore().equals(input.getGetScore()));
            input.setGetScore(input.getGetScore());
        }

        // 声明一个用户提交答题明细实体
        UserAnswerDet UserAnswerDet = input.MapToEntity();
        // 调用数据库的增加或者修改方法
        saveOrUpdate(UserAnswerDet);
        // 把传输模型返回给前端
        return UserAnswerDet.MapToDto();
    }

    /**
     * 用户提交答题明细删除
     */
    @Override
    public void Delete(IdInput input) {
        UserAnswerDet entity = UserAnswerDetMapper.selectById(input.getId());
        UserAnswerDetMapper.deleteById(entity);
    }

    /**
     * 用户提交答题明细批量删除
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
