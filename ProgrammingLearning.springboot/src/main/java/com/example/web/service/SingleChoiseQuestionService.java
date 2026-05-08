package com.example.web.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.web.dto.*;
import com.example.web.dto.query.*;
import com.example.web.entity.*;
import com.example.web.tools.dto.*;
import com.example.web.enums.*;
import java.lang.reflect.InvocationTargetException;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.IOException;
import jakarta.servlet.http.HttpServletResponse;
/**
 * 单选题功能的Service接口的定义清单
 */
public interface SingleChoiseQuestionService extends IService<SingleChoiseQuestion> {

    /**
     * 单选题的分页查询方法接口定义
     */
    public PagedResult<SingleChoiseQuestionDto> List(SingleChoiseQuestionPagedInput input) ;
    /**
     * 单选题的新增或者修改方法接口定义
     */
    public SingleChoiseQuestionDto CreateOrEdit(SingleChoiseQuestionDto input);

     /**
     * 获取单选题信息
     */
    public SingleChoiseQuestionDto Get(SingleChoiseQuestionPagedInput input);
 	 /**
     * 单选题删除
     */
    public void Delete(IdInput input);

    /**
     * 单选题批量删除
     */
    public void BatchDelete(IdsInput input);
  

}
