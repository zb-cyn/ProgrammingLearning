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
 * 系统标签功能的Service接口的定义清单
 */
public interface SysLabelService extends IService<SysLabel> {

    /**
     * 系统标签的分页查询方法接口定义
     */
    public PagedResult<SysLabelDto> List(SysLabelPagedInput input) ;
    /**
     * 系统标签的新增或者修改方法接口定义
     */
    public SysLabelDto CreateOrEdit(SysLabelDto input);

     /**
     * 获取系统标签信息
     */
    public SysLabelDto Get(SysLabelPagedInput input);
 	 /**
     * 系统标签删除
     */
    public void Delete(IdInput input);

    /**
     * 系统标签批量删除
     */
    public void BatchDelete(IdsInput input);
  

}
