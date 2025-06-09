package com.example.sens.mapper;

import com.example.sens.entity.Note;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * (biz_note)数据Mapper
 *
 * @author saysky
 * @since 2024-02-18 12:39:38
 * @description 
*/
@Mapper
public interface NoteMapper extends BaseMapper<Note> {

}
