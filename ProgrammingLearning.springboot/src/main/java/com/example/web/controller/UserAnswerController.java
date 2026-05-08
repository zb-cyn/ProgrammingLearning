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
 * 用户提交答题控制器
 */
@RestController()
@RequestMapping("/UserAnswer")
public class UserAnswerController {
    @Autowired()
    private UserAnswerService UserAnswerService;
    @Autowired()
    private UserAnswerMapper UserAnswerMapper;

    /**
     * 用户提交答题分页查询
     */
    @RequestMapping(value = "/List", method = RequestMethod.POST)
    @SneakyThrows
    public PagedResult<UserAnswerDto> List(@RequestBody UserAnswerPagedInput input) {
        return UserAnswerService.List(input);
    }

    /**
     * 单个用户提交答题查询接口
     */
    @RequestMapping(value = "/Get", method = RequestMethod.POST)
    @SneakyThrows
    public UserAnswerDto Get(@RequestBody UserAnswerPagedInput input) {

        return UserAnswerService.Get(input);
    }

    /**
     * 用户提交答题创建或则修改
     */
    @RequestMapping(value = "/CreateOrEdit", method = RequestMethod.POST)
    public UserAnswerDto CreateOrEdit(@RequestBody UserAnswerDto input) throws Exception {
        return UserAnswerService.CreateOrEdit(input);
    }

    /**
     * 用户提交答题删除
     */
    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    public void Delete(@RequestBody IdInput input) {
        UserAnswerService.Delete(input);
    }

    /**
     * 用户提交答题批量删除
     */
    @RequestMapping(value = "/BatchDelete", method = RequestMethod.POST)
    public void BatchDelete(@RequestBody IdsInput input) {
        UserAnswerService.BatchDelete(input);
    }

    /**
     * 检查是否存在上一次没有完成的
     */
    @RequestMapping(value = "/CheckIsExist", method = RequestMethod.POST)
    @SneakyThrows
    public UserAnswerDto CheckIsExist(@RequestBody UserAnswerPagedInput input) {
        return UserAnswerService.CheckIsExist(input);
    }

    /**
     * 开始答题
     */
    @RequestMapping(value = "/BeginAnswer", method = RequestMethod.POST)
    @SneakyThrows
    public UserAnswerDto BeginAnswer(@RequestBody UserAnswerDto input) {
        return UserAnswerService.BeginAnswer(input);
    }

    /**
     * 提交答题
     */
    @RequestMapping(value = "/Sumbit", method = RequestMethod.POST)
    public void Sumbit(@RequestBody UserAnswerDto input) {
        UserAnswerService.Sumbit(input);
    }
    /**
     * 完成阅卷
     */
    @RequestMapping(value = "/FinishMarking", method = RequestMethod.POST)
    public void FinishMarking(@RequestBody UserAnswerDto input) {
        UserAnswerService.FinishMarking(input);
    }

}
