package com.example.web.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.web.dto.AskedQuestionsDto;
import com.example.web.dto.query.AskedQuestionsPagedInput;
import com.example.web.entity.AskedQuestions;
import com.example.web.mapper.AppUserMapper;
import com.example.web.mapper.AskedQuestionsMapper;
import com.example.web.service.AskedQuestionsService;
import com.example.web.tools.Extension;
import com.example.web.tools.dto.IdInput;
import com.example.web.tools.dto.IdsInput;
import com.example.web.tools.dto.PagedResult;

import lombok.SneakyThrows;

/**
 * 常见问题手册功能实现类
 */
@Service
public class AskedQuestionsServiceImpl extends ServiceImpl<AskedQuestionsMapper, AskedQuestions>
        implements AskedQuestionsService {

    /**
     * 操作数据库AppUser表mapper对象
     */
    @Autowired
    private AppUserMapper AppUserMapper;
    /**
     * 操作数据库的AskedQuestions表mapper对象
     */
    @Autowired
    private AskedQuestionsMapper AskedQuestionsMapper;

    /**
     * 构建表查询sql
     */
    private LambdaQueryWrapper<AskedQuestions> BuilderQuery(AskedQuestionsPagedInput input) {
        // 声明一个支持常见问题手册查询的(拉姆达)表达式
        LambdaQueryWrapper<AskedQuestions> queryWrapper = Wrappers.<AskedQuestions>lambdaQuery()
                .eq(input.getId() != null && input.getId() != 0, AskedQuestions::getId, input.getId())
                .eq(input.getCreatorId() != null, AskedQuestions::getCreatorId, input.getCreatorId());
        // 如果前端搜索传入查询条件则拼接查询条件
        if (Extension.isNotNullOrEmpty(input.getTitle())) {
            queryWrapper = queryWrapper.like(AskedQuestions::getTitle, input.getTitle());
        }
        if (Extension.isNotNullOrEmpty(input.getType())) {
            queryWrapper = queryWrapper.like(AskedQuestions::getType, input.getType());
        }
        if (Extension.isNotNullOrEmpty(input.getContent())) {
            queryWrapper = queryWrapper.like(AskedQuestions::getContent, input.getContent());
        }
        return queryWrapper;
    }

    /**
     * 处理常见问题手册对于的外键数据
     */
    private List<AskedQuestionsDto> DispatchItem(List<AskedQuestionsDto> items)
            throws InvocationTargetException, IllegalAccessException {

        for (AskedQuestionsDto item : items) {
        }

        return items;
    }

    /**
     * 得到列表里面的分类并且去重
     */
    @SneakyThrows
    @Override
    public List<String> GetTypeList() {
        List<AskedQuestions> items = AskedQuestionsMapper.selectList(null);
        return items.stream().map(AskedQuestions::getType).distinct().collect(Collectors.toList());
    }

    /**
     * 常见问题手册分页查询
     */
    @SneakyThrows
    @Override
    public PagedResult<AskedQuestionsDto> List(AskedQuestionsPagedInput input) {
        // 构建where条件+排序
        LambdaQueryWrapper<AskedQuestions> queryWrapper = BuilderQuery(input);

        // 按创建时间从大到小排序 最新的显示在最前面
        queryWrapper = queryWrapper.orderByDesc(AskedQuestions::getCreationTime);
        // 构建一个分页查询的model
        Page<AskedQuestions> page = new Page<>(input.getPage(), input.getLimit());
        // 从数据库进行分页查询获取常见问题手册数据
        IPage<AskedQuestions> pageRecords = AskedQuestionsMapper.selectPage(page, queryWrapper);
        // 获取所有满足条件的数据行数
        Long totalCount = AskedQuestionsMapper.selectCount(queryWrapper);
        // 把AskedQuestions实体转换成AskedQuestions传输模型
        List<AskedQuestionsDto> items = Extension.copyBeanList(pageRecords.getRecords(), AskedQuestionsDto.class);

        DispatchItem(items);
        // 返回一个分页结构给前端
        return PagedResult.GetInstance(items, totalCount);

    }

    /**
     * 单个常见问题手册查询
     */
    @SneakyThrows
    @Override
    public AskedQuestionsDto Get(AskedQuestionsPagedInput input) {
        if (input.getId() == null) {
            return new AskedQuestionsDto();
        }

        PagedResult<AskedQuestionsDto> pagedResult = List(input);
        return pagedResult.getItems().stream().findFirst().orElse(new AskedQuestionsDto());
    }

    /**
     * 常见问题手册创建或者修改
     */
    @SneakyThrows
    @Override
    public AskedQuestionsDto CreateOrEdit(AskedQuestionsDto input) {
        // 声明一个常见问题手册实体
        AskedQuestions AskedQuestions = input.MapToEntity();
        // 调用数据库的增加或者修改方法
        saveOrUpdate(AskedQuestions);
        // 把传输模型返回给前端
        return AskedQuestions.MapToDto();
    }

    /**
     * 有用反馈
     */
    @SneakyThrows
    @Override
    public void AvailCountFeedback(AskedQuestionsDto input) {
        AskedQuestions entity = AskedQuestionsMapper.selectById(input.getId());
        if (entity == null) {
            return;
        }
        entity.setAvailCount(entity.getAvailCount() + 1);
        AskedQuestionsMapper.updateById(entity);
    }

    /**
     * 无效反馈
     */
    @SneakyThrows
    @Override
    public void NoAvailCountFeedback(AskedQuestionsDto input) {
        AskedQuestions entity = AskedQuestionsMapper.selectById(input.getId());
        if (entity == null) {
            return;
        }
        entity.setNoAvailCount(entity.getNoAvailCount() + 1);
        AskedQuestionsMapper.updateById(entity);
    }

    /**
     * 常见问题手册删除
     */
    @Override
    public void Delete(IdInput input) {
        AskedQuestions entity = AskedQuestionsMapper.selectById(input.getId());
        AskedQuestionsMapper.deleteById(entity);
    }

    /**
     * 常见问题手册批量删除
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
