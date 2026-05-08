package com.example.web.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.web.entity.*;
import org.apache.ibatis.annotations.Mapper;

/**
 * 考试表对应的Mapper
 */
@Mapper
public interface TestMapper  extends BaseMapper<Test> {

}
