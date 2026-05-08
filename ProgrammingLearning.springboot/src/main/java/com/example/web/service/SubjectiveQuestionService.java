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
 * 主观题功能的Service接口的定义清单
 */
public interface SubjectiveQuestionService extends IService<SubjectiveQuestion> {

    /**
     * 主观题的分页查询方法接口定义
     */
    public PagedResult<SubjectiveQuestionDto> List(SubjectiveQuestionPagedInput input) ;
    /**
     * 主观题的新增或者修改方法接口定义
     */
    public SubjectiveQuestionDto CreateOrEdit(SubjectiveQuestionDto input);

     /**
     * 获取主观题信息
     */
    public SubjectiveQuestionDto Get(SubjectiveQuestionPagedInput input);
 	 /**
     * 主观题删除
     */
    public void Delete(IdInput input);

    /**
     * 主观题批量删除
     */
    public void BatchDelete(IdsInput input);
  

}
