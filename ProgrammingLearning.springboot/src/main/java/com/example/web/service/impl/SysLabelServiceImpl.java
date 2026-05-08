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
 * 系统标签功能实现类
 */
@Service
public class SysLabelServiceImpl extends ServiceImpl<SysLabelMapper, SysLabel> implements SysLabelService {

	 /**
     * 操作数据库AppUser表mapper对象
     */
    @Autowired
    private AppUserMapper AppUserMapper;
    /**
     * 操作数据库的SysLabel表mapper对象
     */
    @Autowired
    private SysLabelMapper SysLabelMapper;

  
   /**
     * 构建表查询sql
     */
    private LambdaQueryWrapper<SysLabel> BuilderQuery(SysLabelPagedInput input) {
       //声明一个支持系统标签查询的(拉姆达)表达式
        LambdaQueryWrapper<SysLabel> queryWrapper = Wrappers.<SysLabel>lambdaQuery()
                .eq(input.getId() != null && input.getId() != 0, SysLabel::getId, input.getId())
                .eq(input.getCreatorId() != null, SysLabel::getCreatorId, input.getCreatorId());
   //如果前端搜索传入查询条件则拼接查询条件
        if (Extension.isNotNullOrEmpty(input.getName())) {
             queryWrapper = queryWrapper.like(SysLabel::getName, input.getName());
       	 }
        if (Extension.isNotNullOrEmpty(input.getSort())) {
             queryWrapper = queryWrapper.like(SysLabel::getSort, input.getSort());
       	 }
        if (Extension.isNotNullOrEmpty(input.getCode())) {
             queryWrapper = queryWrapper.like(SysLabel::getCode, input.getCode());
       	 }
      return queryWrapper;
    }
  
    /**
     * 处理系统标签对于的外键数据
     */
   private List<SysLabelDto> DispatchItem(List<SysLabelDto> items) throws InvocationTargetException, IllegalAccessException {
   
    for (SysLabelDto item : items) {        
          //查询出关联的创建用户信息
           
            AppUser  CreatorAppUserEntity= AppUserMapper.selectList(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getId,item.getCreatorId())).stream().findFirst().orElse(new AppUser());
  				 item.setCreatorAppUserDto(CreatorAppUserEntity.MapToDto());        }
     return items; 
   }
  
    /**
     * 系统标签分页查询
     */
    @SneakyThrows
    @Override
    public PagedResult<SysLabelDto> List(SysLabelPagedInput input) {
			//构建where条件+排序
        LambdaQueryWrapper<SysLabel> queryWrapper = BuilderQuery(input);
       
        //按创建时间从大到小排序 最新的显示在最前面
        queryWrapper=queryWrapper.orderByDesc(SysLabel::getCreationTime);
        //构建一个分页查询的model
        Page<SysLabel> page = new Page<>(input.getPage(), input.getLimit());
         //从数据库进行分页查询获取系统标签数据
        IPage<SysLabel> pageRecords= SysLabelMapper.selectPage(page, queryWrapper);
        //获取所有满足条件的数据行数
        Long totalCount= SysLabelMapper.selectCount(queryWrapper);
        //把SysLabel实体转换成SysLabel传输模型
        List<SysLabelDto> items= Extension.copyBeanList(pageRecords.getRecords(),SysLabelDto.class);
        //计算表的数据
        items = DispatchItem(items);

      
        //返回一个分页结构给前端
        return PagedResult.GetInstance(items,totalCount);

    }
  
    /**
     * 单个系统标签查询
     */
    @SneakyThrows
    @Override
    public SysLabelDto Get(SysLabelPagedInput input) {
       if(input.getId()==null)
        {
            return new SysLabelDto();
        }
        PagedResult<SysLabelDto>  pagedResult =List(input);
        return pagedResult.getItems().stream().findFirst().orElse(new SysLabelDto());   
    }

    /**
     *系统标签创建或者修改
     */
    @SneakyThrows
    @Override
    public SysLabelDto CreateOrEdit(SysLabelDto input) {
        //声明一个系统标签实体
        SysLabel SysLabel=input.MapToEntity();  
        //调用数据库的增加或者修改方法
        saveOrUpdate(SysLabel);
        //把传输模型返回给前端
        return SysLabel.MapToDto();
    }
    /**
     * 系统标签删除
     */
    @Override
    public void Delete(IdInput input) {
        SysLabel entity = SysLabelMapper.selectById(input.getId());
        SysLabelMapper.deleteById(entity);
    }

    /**
     * 系统标签批量删除
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
