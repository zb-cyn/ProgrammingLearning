package com.example.web.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.web.entity.*;
import org.apache.ibatis.annotations.Mapper;

/**
 * 考试日志表对应的Mapper
 */
@Mapper
public interface UserAnswerLogMapper  extends BaseMapper<UserAnswerLog> {

}
