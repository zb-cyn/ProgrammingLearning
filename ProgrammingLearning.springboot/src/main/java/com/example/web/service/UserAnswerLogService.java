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
 * 考试日志功能的Service接口的定义清单
 */
public interface UserAnswerLogService extends IService<UserAnswerLog> {

    /**
     * 考试日志的分页查询方法接口定义
     */
    public PagedResult<UserAnswerLogDto> List(UserAnswerLogPagedInput input) ;
    /**
     * 考试日志的新增或者修改方法接口定义
     */
    public UserAnswerLogDto CreateOrEdit(UserAnswerLogDto input);

     /**
     * 获取考试日志信息
     */
    public UserAnswerLogDto Get(UserAnswerLogPagedInput input);
 	 /**
     * 考试日志删除
     */
    public void Delete(IdInput input);

    /**
     * 考试日志批量删除
     */
    public void BatchDelete(IdsInput input);
  

}
