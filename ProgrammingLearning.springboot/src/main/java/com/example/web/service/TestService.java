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
 * 考试功能的Service接口的定义清单
 */
public interface TestService extends IService<Test> {

    /**
     * 考试的分页查询方法接口定义
     */
    public PagedResult<TestDto> List(TestPagedInput input) ;
    /**
     * 考试的新增或者修改方法接口定义
     */
    public TestDto CreateOrEdit(TestDto input);

     /**
     * 获取考试信息
     */
    public TestDto Get(TestPagedInput input);
 	 /**
     * 考试删除
     */
    public void Delete(IdInput input);

    /**
     * 考试批量删除
     */
    public void BatchDelete(IdsInput input);
  

}
