package com.example.sens.mapper;

import com.example.sens.entity.Mark;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * (biz_mark)数据Mapper
 *
 * @author saysky
 * @since 2024-02-18 12:39:38
 * @description 
*/
@Mapper
public interface MarkMapper extends BaseMapper<Mark> {

    int countByUserId(@Param("userId") Long userId, @Param("type") Integer type);
}
