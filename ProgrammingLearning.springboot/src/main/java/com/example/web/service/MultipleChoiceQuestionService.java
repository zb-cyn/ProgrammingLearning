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
 * 多选题功能的Service接口的定义清单
 */
public interface MultipleChoiceQuestionService extends IService<MultipleChoiceQuestion> {

    /**
     * 多选题的分页查询方法接口定义
     */
    public PagedResult<MultipleChoiceQuestionDto> List(MultipleChoiceQuestionPagedInput input) ;
    /**
     * 多选题的新增或者修改方法接口定义
     */
    public MultipleChoiceQuestionDto CreateOrEdit(MultipleChoiceQuestionDto input);

     /**
     * 获取多选题信息
     */
    public MultipleChoiceQuestionDto Get(MultipleChoiceQuestionPagedInput input);
 	 /**
     * 多选题删除
     */
    public void Delete(IdInput input);

    /**
     * 多选题批量删除
     */
    public void BatchDelete(IdsInput input);
  

}
