package com.example.web.controller;
import com.example.web.SysConst;
import com.example.web.dto.*;
import com.example.web.dto.query.*;
import com.example.web.entity.*;
import com.example.web.mapper.*;
import com.example.web.service.*;
import com.example.web.tools.dto.*;
import com.example.web.tools.exception.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import lombok.SneakyThrows;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;
import jakarta.servlet.http.HttpServletResponse;
/**
 * 系统标签控制器
 */
@RestController()
@RequestMapping("/SysLabel")
public class SysLabelController {
    @Autowired()
    private  SysLabelService SysLabelService;
    @Autowired()
    private SysLabelMapper SysLabelMapper;
    /**
     * 系统标签分页查询
     */
    @RequestMapping(value = "/List", method = RequestMethod.POST)
    @SneakyThrows
    public PagedResult<SysLabelDto> List(@RequestBody SysLabelPagedInput input)  {
        return SysLabelService.List(input);
    }
     /**
     * 单个系统标签查询接口
     */
    @RequestMapping(value = "/Get", method = RequestMethod.POST)
    @SneakyThrows
    public SysLabelDto Get(@RequestBody SysLabelPagedInput input) {

        return SysLabelService.Get(input);
    }
  
    /**
     * 系统标签创建或则修改
     */
    @RequestMapping(value = "/CreateOrEdit", method = RequestMethod.POST)
    public SysLabelDto CreateOrEdit(@RequestBody SysLabelDto input) throws Exception {
        return SysLabelService.CreateOrEdit(input);
    }
    /**
     * 系统标签删除
     */
    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    public void Delete(@RequestBody IdInput input)
    {
        SysLabelService.Delete(input);
    }

    /**
     * 系统标签批量删除
     */
    @RequestMapping(value = "/BatchDelete", method = RequestMethod.POST)
    public void BatchDelete(@RequestBody IdsInput input)
    {
        SysLabelService.BatchDelete(input);
    }
  

 
}
