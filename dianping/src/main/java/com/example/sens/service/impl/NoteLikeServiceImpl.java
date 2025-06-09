package com.example.sens.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sens.entity.Note;
import com.example.sens.entity.NoteLike;
import com.example.sens.mapper.NoteLikeMapper;
import com.example.sens.service.NoteLikeService;
import com.example.sens.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 服务接口实现
 *
 * @author saysky
 * @description
 * @since 2024-02-18 12:39:38
 */
@Slf4j
@Service
public class NoteLikeServiceImpl extends ServiceImpl<NoteLikeMapper, NoteLike> implements NoteLikeService {


    @Autowired
    private NoteService noteService;

    @Override
    public void like(Long noteId, Long userId) {
        Note note = noteService.getById(noteId);
        if (note == null) {
            return;
        }

        NoteLike noteLike = new NoteLike();
        noteLike.setNoteId(noteId);
        noteLike.setUserId(userId);
        noteLike.setCreateTime(new Date());
        this.save(noteLike);

        // 更新点赞数
        updateLikeSize(note);

    }

    private void updateLikeSize(Note note) {
        // 更新点赞数
        LambdaQueryWrapper<NoteLike> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(NoteLike::getNoteId, note.getId());
        int count = this.count(queryWrapper);
        note.setLikeSize(count);
        noteService.updateById(note);
    }

    @Override
    public void cancelLike(Long noteId, Long userId) {
        Note note = noteService.getById(noteId);
        if (note == null) {
            return;
        }

        LambdaQueryWrapper<NoteLike> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(NoteLike::getNoteId, noteId).eq(NoteLike::getUserId, userId);
        this.remove(queryWrapper);

        // 更新点赞数
        updateLikeSize(note);
    }

    @Override
    public boolean isLike(Long noteId, Long userId) {
        LambdaQueryWrapper<NoteLike> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(NoteLike::getNoteId, noteId).eq(NoteLike::getUserId, userId);
        List<NoteLike> noteLikes = this.list(queryWrapper);
        return CollectionUtil.isNotEmpty(noteLikes);
    }

    @Override
    public int countByUserId(Long userId) {
        return baseMapper.countByUserId(userId);
    }
}