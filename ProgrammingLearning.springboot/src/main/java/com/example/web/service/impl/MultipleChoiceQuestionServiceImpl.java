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
 * 多选题功能实现类
 */
@Service
public class MultipleChoiceQuestionServiceImpl extends ServiceImpl<MultipleChoiceQuestionMapper, MultipleChoiceQuestion> implements MultipleChoiceQuestionService {

	 /**
     * 操作数据库AppUser表mapper对象
     */
    @Autowired
    private AppUserMapper AppUserMapper;
    /**
     * 操作数据库的MultipleChoiceQuestion表mapper对象
     */
    @Autowired
    private MultipleChoiceQuestionMapper MultipleChoiceQuestionMapper;


    @Autowired
    private QuestionnaireDetMapper QuestionnaireDetMapper;

    /**
     * 构建表查询sql
     */
    private LambdaQueryWrapper<MultipleChoiceQuestion> BuilderQuery(MultipleChoiceQuestionPagedInput input) {
       //声明一个支持多选题查询的(拉姆达)表达式
        LambdaQueryWrapper<MultipleChoiceQuestion> queryWrapper = Wrappers.<MultipleChoiceQuestion>lambdaQuery()
                .eq(input.getId() != null && input.getId() != 0, MultipleChoiceQuestion::getId, input.getId())
                .eq(input.getCreatorId() != null, MultipleChoiceQuestion::getCreatorId, input.getCreatorId());
   //如果前端搜索传入查询条件则拼接查询条件
        if (Extension.isNotNullOrEmpty(input.getTitle())) {
             queryWrapper = queryWrapper.like(MultipleChoiceQuestion::getTitle, input.getTitle());
       	 }
        if (Extension.isNotNullOrEmpty(input.getOptionA())) {
             queryWrapper = queryWrapper.like(MultipleChoiceQuestion::getOptionA, input.getOptionA());
       	 }
        if (Extension.isNotNullOrEmpty(input.getOptionB())) {
             queryWrapper = queryWrapper.like(MultipleChoiceQuestion::getOptionB, input.getOptionB());
       	 }
        if (Extension.isNotNullOrEmpty(input.getOptionC())) {
             queryWrapper = queryWrapper.like(MultipleChoiceQuestion::getOptionC, input.getOptionC());
       	 }
        if (Extension.isNotNullOrEmpty(input.getOptionD())) {
             queryWrapper = queryWrapper.like(MultipleChoiceQuestion::getOptionD, input.getOptionD());
       	 }
        if (Extension.isNotNullOrEmpty(input.getAnalyzeResult())) {
             queryWrapper = queryWrapper.like(MultipleChoiceQuestion::getAnalyzeResult, input.getAnalyzeResult());
       	 }
        if (Extension.isNotNullOrEmpty(input.getRightAnswer())) {
             queryWrapper = queryWrapper.like(MultipleChoiceQuestion::getRightAnswer, input.getRightAnswer());
       	 }
        if (Extension.isNotNullOrEmpty(input.getType())) {
             queryWrapper = queryWrapper.like(MultipleChoiceQuestion::getType, input.getType());
       	 }
      return queryWrapper;
    }
  
    /**
     * 处理多选题对于的外键数据
     */
   private List<MultipleChoiceQuestionDto> DispatchItem(List<MultipleChoiceQuestionDto> items) throws InvocationTargetException, IllegalAccessException {
   
    for (MultipleChoiceQuestionDto item : items) {        
          //查询出关联的创建用户信息
           
            AppUser  CreatorAppUserEntity= AppUserMapper.selectList(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getId,item.getCreatorId())).stream().findFirst().orElse(new AppUser());
  				 item.setCreatorAppUserDto(CreatorAppUserEntity.MapToDto());        }
     return items; 
   }
  
    /**
     * 多选题分页查询
     */
    @SneakyThrows
    @Override
    public PagedResult<MultipleChoiceQuestionDto> List(MultipleChoiceQuestionPagedInput input) {
			//构建where条件+排序
        LambdaQueryWrapper<MultipleChoiceQuestion> queryWrapper = BuilderQuery(input);
       
        //按创建时间从大到小排序 最新的显示在最前面
        queryWrapper=queryWrapper.orderByDesc(MultipleChoiceQuestion::getCreationTime);
        //构建一个分页查询的model
        Page<MultipleChoiceQuestion> page = new Page<>(input.getPage(), input.getLimit());
         //从数据库进行分页查询获取多选题数据
        IPage<MultipleChoiceQuestion> pageRecords= MultipleChoiceQuestionMapper.selectPage(page, queryWrapper);
        //获取所有满足条件的数据行数
        Long totalCount= MultipleChoiceQuestionMapper.selectCount(queryWrapper);
        //把MultipleChoiceQuestion实体转换成MultipleChoiceQuestion传输模型
        List<MultipleChoiceQuestionDto> items= Extension.copyBeanList(pageRecords.getRecords(),MultipleChoiceQuestionDto.class);
        //计算表的数据
        items = DispatchItem(items);

      
        //返回一个分页结构给前端
        return PagedResult.GetInstance(items,totalCount);

    }
  
    /**
     * 单个多选题查询
     */
    @SneakyThrows
    @Override
    public MultipleChoiceQuestionDto Get(MultipleChoiceQuestionPagedInput input) {
       if(input.getId()==null)
        {
            return new MultipleChoiceQuestionDto();
        }
        PagedResult<MultipleChoiceQuestionDto>  pagedResult =List(input);
        return pagedResult.getItems().stream().findFirst().orElse(new MultipleChoiceQuestionDto());   
    }

    /**
     *多选题创建或者修改
     */
    @SneakyThrows
    @Override
    public MultipleChoiceQuestionDto CreateOrEdit(MultipleChoiceQuestionDto input) {
        //声明一个多选题实体
        MultipleChoiceQuestion MultipleChoiceQuestion=input.MapToEntity();  
        //调用数据库的增加或者修改方法
        saveOrUpdate(MultipleChoiceQuestion);
        //把传输模型返回给前端
        return MultipleChoiceQuestion.MapToDto();
    }
    /**
     * 多选题删除
     */
    @Override
    public void Delete(IdInput input) {
        MultipleChoiceQuestion entity = MultipleChoiceQuestionMapper.selectById(input.getId());
        Long selectedCount = QuestionnaireDetMapper.selectCount(Wrappers.<QuestionnaireDet>lambdaQuery()
                .eq(QuestionnaireDet::getQuestionId, input.getId())
                .eq(QuestionnaireDet::getQuestionType, QuestionTypeEnum.多选题.index()));
        if (selectedCount > 0) {
            throw new RuntimeException("该题型已被使用无法删除");
        }
        MultipleChoiceQuestionMapper.deleteById(entity);
    }

    /**
     * 多选题批量删除
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
