package com.example.sens.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.sens.entity.NoteComment;
import com.example.sens.entity.NoteLike;

/**
 * 服务接口
 *
 * @author saysky
 * @description
 * @since 2024-02-18 12:39:38
 */
public interface NoteCommentService extends IService<NoteComment> {

    /**
     * 统计用户的评论数
     *
     * @param userId
     * @return
     */
    int countByUserId(Long userId);

    /**
     * 统计笔记的评论数
     *
     * @param noteId
     * @return
     */
    int countByNoteId(Long noteId);

    /**
     * 获取笔记评论分页
     *
     * @param userId
     * @return
     */
    IPage<NoteComment> getNoteCommentPage(Long userId,  String content, Page<NoteComment> page);
}
