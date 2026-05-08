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
/**
 * 考试日志功能实现类
 */
@Service
public class UserAnswerLogServiceImpl extends ServiceImpl<UserAnswerLogMapper, UserAnswerLog> implements UserAnswerLogService {

	 /**
     * 操作数据库AppUser表mapper对象
     */
    @Autowired
    private AppUserMapper AppUserMapper;
    /**
     * 操作数据库的UserAnswerLog表mapper对象
     */
    @Autowired
    private UserAnswerLogMapper UserAnswerLogMapper;

  
   /**
     * 构建表查询sql
     */
    private LambdaQueryWrapper<UserAnswerLog> BuilderQuery(UserAnswerLogPagedInput input) {
       //声明一个支持考试日志查询的(拉姆达)表达式
        LambdaQueryWrapper<UserAnswerLog> queryWrapper = Wrappers.<UserAnswerLog>lambdaQuery()
                .eq(input.getId() != null && input.getId() != 0, UserAnswerLog::getId, input.getId())
                .eq(input.getCreatorId() != null, UserAnswerLog::getCreatorId, input.getCreatorId());
   //如果前端搜索传入查询条件则拼接查询条件
      return queryWrapper;
    }
  
    /**
     * 处理考试日志对于的外键数据
     */
   private List<UserAnswerLogDto> DispatchItem(List<UserAnswerLogDto> items) throws InvocationTargetException, IllegalAccessException {
          
       for (UserAnswerLogDto item : items) {       }
       
     return items; 
   }
  
    /**
     * 考试日志分页查询
     */
    @SneakyThrows
    @Override
    public PagedResult<UserAnswerLogDto> List(UserAnswerLogPagedInput input) {
			//构建where条件+排序
        LambdaQueryWrapper<UserAnswerLog> queryWrapper = BuilderQuery(input);
       
        //按创建时间从大到小排序 最新的显示在最前面
        queryWrapper=queryWrapper.orderByDesc(UserAnswerLog::getCreationTime);
        //构建一个分页查询的model
        Page<UserAnswerLog> page = new Page<>(input.getPage(), input.getLimit());
         //从数据库进行分页查询获取考试日志数据
        IPage<UserAnswerLog> pageRecords= UserAnswerLogMapper.selectPage(page, queryWrapper);
        //获取所有满足条件的数据行数
        Long totalCount= UserAnswerLogMapper.selectCount(queryWrapper);
        //把UserAnswerLog实体转换成UserAnswerLog传输模型
        List<UserAnswerLogDto> items= Extension.copyBeanList(pageRecords.getRecords(),UserAnswerLogDto.class);

		   DispatchItem(items);
        //返回一个分页结构给前端
        return PagedResult.GetInstance(items,totalCount);

    }
  
    /**
     * 单个考试日志查询
     */
    @SneakyThrows
    @Override
    public UserAnswerLogDto Get(UserAnswerLogPagedInput input) {
       if(input.getId()==null)
        {
         return new UserAnswerLogDto();
        }
      
       PagedResult<UserAnswerLogDto> pagedResult = List(input);
        return pagedResult.getItems().stream().findFirst().orElse(new UserAnswerLogDto()); 
    }

    /**
     *考试日志创建或者修改
     */
    @SneakyThrows
    @Override
    public UserAnswerLogDto CreateOrEdit(UserAnswerLogDto input) {
        //声明一个考试日志实体
        UserAnswerLog UserAnswerLog=input.MapToEntity();  
        //调用数据库的增加或者修改方法
        saveOrUpdate(UserAnswerLog);
        //把传输模型返回给前端
        return UserAnswerLog.MapToDto();
    }
    /**
     * 考试日志删除
     */
    @Override
    public void Delete(IdInput input) {
        UserAnswerLog entity = UserAnswerLogMapper.selectById(input.getId());
        UserAnswerLogMapper.deleteById(entity);
    }

    /**
     * 考试日志批量删除
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
