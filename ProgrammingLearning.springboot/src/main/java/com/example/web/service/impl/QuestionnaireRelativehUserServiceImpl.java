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
 * 问卷指定用户功能实现类
 */
@Service
public class QuestionnaireRelativehUserServiceImpl extends ServiceImpl<QuestionnaireRelativehUserMapper, QuestionnaireRelativehUser> implements QuestionnaireRelativehUserService {

	 /**
     * 操作数据库AppUser表mapper对象
     */
    @Autowired
    private AppUserMapper AppUserMapper;
    /**
     * 操作数据库的QuestionnaireRelativehUser表mapper对象
     */
    @Autowired
    private QuestionnaireRelativehUserMapper QuestionnaireRelativehUserMapper;

  
   /**
     * 构建表查询sql
     */
    private LambdaQueryWrapper<QuestionnaireRelativehUser> BuilderQuery(QuestionnaireRelativehUserPagedInput input) {
       //声明一个支持问卷指定用户查询的(拉姆达)表达式
        LambdaQueryWrapper<QuestionnaireRelativehUser> queryWrapper = Wrappers.<QuestionnaireRelativehUser>lambdaQuery()
                .eq(input.getId() != null && input.getId() != 0, QuestionnaireRelativehUser::getId, input.getId())
                .eq(input.getCreatorId() != null, QuestionnaireRelativehUser::getCreatorId, input.getCreatorId());
   //如果前端搜索传入查询条件则拼接查询条件
      return queryWrapper;
    }
  
    /**
     * 处理问卷指定用户对于的外键数据
     */
   private List<QuestionnaireRelativehUserDto> DispatchItem(List<QuestionnaireRelativehUserDto> items) throws InvocationTargetException, IllegalAccessException {
          
       for (QuestionnaireRelativehUserDto item : items) {       }
       
     return items; 
   }
  
    /**
     * 问卷指定用户分页查询
     */
    @SneakyThrows
    @Override
    public PagedResult<QuestionnaireRelativehUserDto> List(QuestionnaireRelativehUserPagedInput input) {
			//构建where条件+排序
        LambdaQueryWrapper<QuestionnaireRelativehUser> queryWrapper = BuilderQuery(input);
       
        //按创建时间从大到小排序 最新的显示在最前面
        queryWrapper=queryWrapper.orderByDesc(QuestionnaireRelativehUser::getCreationTime);
        //构建一个分页查询的model
        Page<QuestionnaireRelativehUser> page = new Page<>(input.getPage(), input.getLimit());
         //从数据库进行分页查询获取问卷指定用户数据
        IPage<QuestionnaireRelativehUser> pageRecords= QuestionnaireRelativehUserMapper.selectPage(page, queryWrapper);
        //获取所有满足条件的数据行数
        Long totalCount= QuestionnaireRelativehUserMapper.selectCount(queryWrapper);
        //把QuestionnaireRelativehUser实体转换成QuestionnaireRelativehUser传输模型
        List<QuestionnaireRelativehUserDto> items= Extension.copyBeanList(pageRecords.getRecords(),QuestionnaireRelativehUserDto.class);

		   DispatchItem(items);
        //返回一个分页结构给前端
        return PagedResult.GetInstance(items,totalCount);

    }
  
    /**
     * 单个问卷指定用户查询
     */
    @SneakyThrows
    @Override
    public QuestionnaireRelativehUserDto Get(QuestionnaireRelativehUserPagedInput input) {
       if(input.getId()==null)
        {
         return new QuestionnaireRelativehUserDto();
        }
      
       PagedResult<QuestionnaireRelativehUserDto> pagedResult = List(input);
        return pagedResult.getItems().stream().findFirst().orElse(new QuestionnaireRelativehUserDto()); 
    }

    /**
     *问卷指定用户创建或者修改
     */
    @SneakyThrows
    @Override
    public QuestionnaireRelativehUserDto CreateOrEdit(QuestionnaireRelativehUserDto input) {
        //声明一个问卷指定用户实体
        QuestionnaireRelativehUser QuestionnaireRelativehUser=input.MapToEntity();  
        //调用数据库的增加或者修改方法
        saveOrUpdate(QuestionnaireRelativehUser);
        //把传输模型返回给前端
        return QuestionnaireRelativehUser.MapToDto();
    }
    /**
     * 问卷指定用户删除
     */
    @Override
    public void Delete(IdInput input) {
        QuestionnaireRelativehUser entity = QuestionnaireRelativehUserMapper.selectById(input.getId());
        QuestionnaireRelativehUserMapper.deleteById(entity);
    }

    /**
     * 问卷指定用户批量删除
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
