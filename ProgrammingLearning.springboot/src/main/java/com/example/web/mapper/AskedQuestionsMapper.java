package com.example.web.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.web.entity.*;
import org.apache.ibatis.annotations.Mapper;

/**
 * 常见问题手册表对应的Mapper
 */
@Mapper
public interface AskedQuestionsMapper  extends BaseMapper<AskedQuestions> {

}
