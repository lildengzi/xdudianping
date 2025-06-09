package com.example.sens.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.sens.entity.Note;
import com.example.sens.entity.NoteLike;
import org.apache.ibatis.annotations.Mapper;

/**
 * (biz_note_like)数据Mapper
 *
 * @author saysky
 * @since 2024-02-18 12:39:38
 * @description 
*/
@Mapper
public interface NoteLikeMapper extends BaseMapper<NoteLike> {
    int countByUserId(Long userId);
}
