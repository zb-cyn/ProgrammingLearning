package com.example.web.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.web.entity.*;
import org.apache.ibatis.annotations.Mapper;

/**
 * 多选题表对应的Mapper
 */
@Mapper
public interface MultipleChoiceQuestionMapper  extends BaseMapper<MultipleChoiceQuestion> {

}
