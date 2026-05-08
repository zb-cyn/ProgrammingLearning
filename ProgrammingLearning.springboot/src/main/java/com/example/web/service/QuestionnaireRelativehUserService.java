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
 * 问卷指定用户功能的Service接口的定义清单
 */
public interface QuestionnaireRelativehUserService extends IService<QuestionnaireRelativehUser> {

    /**
     * 问卷指定用户的分页查询方法接口定义
     */
    public PagedResult<QuestionnaireRelativehUserDto> List(QuestionnaireRelativehUserPagedInput input) ;
    /**
     * 问卷指定用户的新增或者修改方法接口定义
     */
    public QuestionnaireRelativehUserDto CreateOrEdit(QuestionnaireRelativehUserDto input);

     /**
     * 获取问卷指定用户信息
     */
    public QuestionnaireRelativehUserDto Get(QuestionnaireRelativehUserPagedInput input);
 	 /**
     * 问卷指定用户删除
     */
    public void Delete(IdInput input);

    /**
     * 问卷指定用户批量删除
     */
    public void BatchDelete(IdsInput input);
  

}
