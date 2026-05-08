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
 * 多选题控制器
 */
@RestController()
@RequestMapping("/MultipleChoiceQuestion")
public class MultipleChoiceQuestionController {
    @Autowired()
    private  MultipleChoiceQuestionService MultipleChoiceQuestionService;
    @Autowired()
    private MultipleChoiceQuestionMapper MultipleChoiceQuestionMapper;
    /**
     * 多选题分页查询
     */
    @RequestMapping(value = "/List", method = RequestMethod.POST)
    @SneakyThrows
    public PagedResult<MultipleChoiceQuestionDto> List(@RequestBody MultipleChoiceQuestionPagedInput input)  {
        return MultipleChoiceQuestionService.List(input);
    }
     /**
     * 单个多选题查询接口
     */
    @RequestMapping(value = "/Get", method = RequestMethod.POST)
    @SneakyThrows
    public MultipleChoiceQuestionDto Get(@RequestBody MultipleChoiceQuestionPagedInput input) {

        return MultipleChoiceQuestionService.Get(input);
    }
  
    /**
     * 多选题创建或则修改
     */
    @RequestMapping(value = "/CreateOrEdit", method = RequestMethod.POST)
    public MultipleChoiceQuestionDto CreateOrEdit(@RequestBody MultipleChoiceQuestionDto input) throws Exception {
        return MultipleChoiceQuestionService.CreateOrEdit(input);
    }
    /**
     * 多选题删除
     */
    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    public void Delete(@RequestBody IdInput input)
    {
        MultipleChoiceQuestionService.Delete(input);
    }

    /**
     * 多选题批量删除
     */
    @RequestMapping(value = "/BatchDelete", method = RequestMethod.POST)
    public void BatchDelete(@RequestBody IdsInput input)
    {
        MultipleChoiceQuestionService.BatchDelete(input);
    }
  

 
}
