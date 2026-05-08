package com.example.web.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.web.entity.*;
import org.apache.ibatis.annotations.Mapper;

/**
 * 判断题型表对应的Mapper
 */
@Mapper
public interface JudgmentQuestionMapper  extends BaseMapper<JudgmentQuestion> {

}
