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
 * 判断题型功能的Service接口的定义清单
 */
public interface JudgmentQuestionService extends IService<JudgmentQuestion> {

    /**
     * 判断题型的分页查询方法接口定义
     */
    public PagedResult<JudgmentQuestionDto> List(JudgmentQuestionPagedInput input) ;
    /**
     * 判断题型的新增或者修改方法接口定义
     */
    public JudgmentQuestionDto CreateOrEdit(JudgmentQuestionDto input);

     /**
     * 获取判断题型信息
     */
    public JudgmentQuestionDto Get(JudgmentQuestionPagedInput input);
 	 /**
     * 判断题型删除
     */
    public void Delete(IdInput input);

    /**
     * 判断题型批量删除
     */
    public void BatchDelete(IdsInput input);
  

}
