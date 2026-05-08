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
 * 主观题功能实现类
 */
@Service
public class SubjectiveQuestionServiceImpl extends ServiceImpl<SubjectiveQuestionMapper, SubjectiveQuestion> implements SubjectiveQuestionService {

	 /**
     * 操作数据库AppUser表mapper对象
     */
    @Autowired
    private AppUserMapper AppUserMapper;
    /**
     * 操作数据库的SubjectiveQuestion表mapper对象
     */
    @Autowired
    private SubjectiveQuestionMapper SubjectiveQuestionMapper;

  
   /**
     * 构建表查询sql
     */
    private LambdaQueryWrapper<SubjectiveQuestion> BuilderQuery(SubjectiveQuestionPagedInput input) {
       //声明一个支持主观题查询的(拉姆达)表达式
        LambdaQueryWrapper<SubjectiveQuestion> queryWrapper = Wrappers.<SubjectiveQuestion>lambdaQuery()
                .eq(input.getId() != null && input.getId() != 0, SubjectiveQuestion::getId, input.getId())
                .eq(input.getCreatorId() != null, SubjectiveQuestion::getCreatorId, input.getCreatorId());
   //如果前端搜索传入查询条件则拼接查询条件
        if (Extension.isNotNullOrEmpty(input.getTitle())) {
             queryWrapper = queryWrapper.like(SubjectiveQuestion::getTitle, input.getTitle());
       	 }
        if (Extension.isNotNullOrEmpty(input.getContent())) {
             queryWrapper = queryWrapper.like(SubjectiveQuestion::getContent, input.getContent());
       	 }
        if (Extension.isNotNullOrEmpty(input.getAnalyzeResult())) {
             queryWrapper = queryWrapper.like(SubjectiveQuestion::getAnalyzeResult, input.getAnalyzeResult());
       	 }
        if (Extension.isNotNullOrEmpty(input.getType())) {
             queryWrapper = queryWrapper.like(SubjectiveQuestion::getType, input.getType());
       	 }
      return queryWrapper;
    }
  
    /**
     * 处理主观题对于的外键数据
     */
   private List<SubjectiveQuestionDto> DispatchItem(List<SubjectiveQuestionDto> items) throws InvocationTargetException, IllegalAccessException {
          
       for (SubjectiveQuestionDto item : items) {       }
       
     return items; 
   }
  
    /**
     * 主观题分页查询
     */
    @SneakyThrows
    @Override
    public PagedResult<SubjectiveQuestionDto> List(SubjectiveQuestionPagedInput input) {
			//构建where条件+排序
        LambdaQueryWrapper<SubjectiveQuestion> queryWrapper = BuilderQuery(input);
       
        //按创建时间从大到小排序 最新的显示在最前面
        queryWrapper=queryWrapper.orderByDesc(SubjectiveQuestion::getCreationTime);
        //构建一个分页查询的model
        Page<SubjectiveQuestion> page = new Page<>(input.getPage(), input.getLimit());
         //从数据库进行分页查询获取主观题数据
        IPage<SubjectiveQuestion> pageRecords= SubjectiveQuestionMapper.selectPage(page, queryWrapper);
        //获取所有满足条件的数据行数
        Long totalCount= SubjectiveQuestionMapper.selectCount(queryWrapper);
        //把SubjectiveQuestion实体转换成SubjectiveQuestion传输模型
        List<SubjectiveQuestionDto> items= Extension.copyBeanList(pageRecords.getRecords(),SubjectiveQuestionDto.class);

		   DispatchItem(items);
        //返回一个分页结构给前端
        return PagedResult.GetInstance(items,totalCount);

    }
  
    /**
     * 单个主观题查询
     */
    @SneakyThrows
    @Override
    public SubjectiveQuestionDto Get(SubjectiveQuestionPagedInput input) {
       if(input.getId()==null)
        {
         return new SubjectiveQuestionDto();
        }
      
       PagedResult<SubjectiveQuestionDto> pagedResult = List(input);
        return pagedResult.getItems().stream().findFirst().orElse(new SubjectiveQuestionDto()); 
    }

    /**
     *主观题创建或者修改
     */
    @SneakyThrows
    @Override
    public SubjectiveQuestionDto CreateOrEdit(SubjectiveQuestionDto input) {
        //声明一个主观题实体
        SubjectiveQuestion SubjectiveQuestion=input.MapToEntity();  
        //调用数据库的增加或者修改方法
        saveOrUpdate(SubjectiveQuestion);
        //把传输模型返回给前端
        return SubjectiveQuestion.MapToDto();
    }
    /**
     * 主观题删除
     */
    @Override
    public void Delete(IdInput input) {
        SubjectiveQuestion entity = SubjectiveQuestionMapper.selectById(input.getId());
        SubjectiveQuestionMapper.deleteById(entity);
    }

    /**
     * 主观题批量删除
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
