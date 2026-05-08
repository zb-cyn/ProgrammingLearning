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
 * 用户提交答题明细功能的Service接口的定义清单
 */
public interface UserAnswerDetService extends IService<UserAnswerDet> {

    /**
     * 用户提交答题明细的分页查询方法接口定义
     */
    public PagedResult<UserAnswerDetDto> List(UserAnswerDetPagedInput input) ;
    /**
     * 用户提交答题明细的新增或者修改方法接口定义
     */
    public UserAnswerDetDto CreateOrEdit(UserAnswerDetDto input);

     /**
     * 获取用户提交答题明细信息
     */
    public UserAnswerDetDto Get(UserAnswerDetPagedInput input);
 	 /**
     * 用户提交答题明细删除
     */
    public void Delete(IdInput input);

    /**
     * 用户提交答题明细批量删除
     */
    public void BatchDelete(IdsInput input);
  

}
