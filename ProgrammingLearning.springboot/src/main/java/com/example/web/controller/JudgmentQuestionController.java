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
 * 判断题型控制器
 */
@RestController()
@RequestMapping("/JudgmentQuestion")
public class JudgmentQuestionController {
    @Autowired()
    private  JudgmentQuestionService JudgmentQuestionService;
    @Autowired()
    private JudgmentQuestionMapper JudgmentQuestionMapper;
    /**
     * 判断题型分页查询
     */
    @RequestMapping(value = "/List", method = RequestMethod.POST)
    @SneakyThrows
    public PagedResult<JudgmentQuestionDto> List(@RequestBody JudgmentQuestionPagedInput input)  {
        return JudgmentQuestionService.List(input);
    }
     /**
     * 单个判断题型查询接口
     */
    @RequestMapping(value = "/Get", method = RequestMethod.POST)
    @SneakyThrows
    public JudgmentQuestionDto Get(@RequestBody JudgmentQuestionPagedInput input) {

        return JudgmentQuestionService.Get(input);
    }
  
    /**
     * 判断题型创建或则修改
     */
    @RequestMapping(value = "/CreateOrEdit", method = RequestMethod.POST)
    public JudgmentQuestionDto CreateOrEdit(@RequestBody JudgmentQuestionDto input) throws Exception {
        return JudgmentQuestionService.CreateOrEdit(input);
    }
    /**
     * 判断题型删除
     */
    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    public void Delete(@RequestBody IdInput input)
    {
        JudgmentQuestionService.Delete(input);
    }

    /**
     * 判断题型批量删除
     */
    @RequestMapping(value = "/BatchDelete", method = RequestMethod.POST)
    public void BatchDelete(@RequestBody IdsInput input)
    {
        JudgmentQuestionService.BatchDelete(input);
    }
  

 
}
