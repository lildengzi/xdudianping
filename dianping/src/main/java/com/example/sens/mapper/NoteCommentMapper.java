package com.example.sens.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sens.entity.Note;
import com.example.sens.entity.NoteComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * (biz_note_comment)数据Mapper
 *
 * @author saysky
 * @description
 * @since 2024-02-18 12:39:38
 */
@Mapper
public interface NoteCommentMapper extends BaseMapper<NoteComment> {

    int countByUserId(Long userId);

    IPage<NoteComment> selectByUserId(Page<NoteComment> page,
                                     @Param("userId") Long userId,
                                     @Param("content") String content);
}
