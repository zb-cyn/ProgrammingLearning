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
 * 考试日志控制器
 */
@RestController()
@RequestMapping("/UserAnswerLog")
public class UserAnswerLogController {
    @Autowired()
    private  UserAnswerLogService UserAnswerLogService;
    @Autowired()
    private UserAnswerLogMapper UserAnswerLogMapper;
    /**
     * 考试日志分页查询
     */
    @RequestMapping(value = "/List", method = RequestMethod.POST)
    @SneakyThrows
    public PagedResult<UserAnswerLogDto> List(@RequestBody UserAnswerLogPagedInput input)  {
        return UserAnswerLogService.List(input);
    }
     /**
     * 单个考试日志查询接口
     */
    @RequestMapping(value = "/Get", method = RequestMethod.POST)
    @SneakyThrows
    public UserAnswerLogDto Get(@RequestBody UserAnswerLogPagedInput input) {

        return UserAnswerLogService.Get(input);
    }
  
    /**
     * 考试日志创建或则修改
     */
    @RequestMapping(value = "/CreateOrEdit", method = RequestMethod.POST)
    public UserAnswerLogDto CreateOrEdit(@RequestBody UserAnswerLogDto input) throws Exception {
        return UserAnswerLogService.CreateOrEdit(input);
    }
    /**
     * 考试日志删除
     */
    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    public void Delete(@RequestBody IdInput input)
    {
        UserAnswerLogService.Delete(input);
    }

    /**
     * 考试日志批量删除
     */
    @RequestMapping(value = "/BatchDelete", method = RequestMethod.POST)
    public void BatchDelete(@RequestBody IdsInput input)
    {
        UserAnswerLogService.BatchDelete(input);
    }
  

 
}
