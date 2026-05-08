package com.example.web.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.web.entity.*;
import org.apache.ibatis.annotations.Mapper;

/**
 * 单选题表对应的Mapper
 */
@Mapper
public interface SingleChoiseQuestionMapper  extends BaseMapper<SingleChoiseQuestion> {

}
