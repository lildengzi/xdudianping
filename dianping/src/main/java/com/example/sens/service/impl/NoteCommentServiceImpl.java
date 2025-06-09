package com.example.sens.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sens.entity.NoteComment;
import com.example.sens.mapper.NoteCommentMapper;
import com.example.sens.service.NoteCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 服务接口实现
 *
 * @author saysky
 * @description
 * @since 2024-02-18 12:39:38
 */
@Slf4j
@Service
public class NoteCommentServiceImpl extends ServiceImpl<NoteCommentMapper, NoteComment> implements NoteCommentService {

    @Autowired
    private NoteCommentMapper dishMapper;


    @Override
    public int countByUserId(Long userId) {
        return dishMapper.countByUserId(userId);
    }

    @Override
    public int countByNoteId(Long noteId) {
        LambdaQueryWrapper<NoteComment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(NoteComment::getNoteId, noteId);
        return this.baseMapper.selectCount(queryWrapper);
    }

    @Override
    public IPage<NoteComment> getNoteCommentPage(Long userId, String content, Page<NoteComment> page) {
        return this.baseMapper.selectByUserId(page, userId, content);
    }
}