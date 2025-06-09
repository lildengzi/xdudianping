package com.example.sens.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.sens.entity.Note;
import com.example.sens.entity.NoteLike;

/**
 * 服务接口
 *
 * @author saysky
 * @since 2024-02-18 12:39:38
 * @description 
 */
public interface NoteLikeService extends IService<NoteLike> {


    /**
     * 点赞
     *
     * @param noteId
     * @param userId
     * @return
     */
    void like(Long noteId, Long userId);

    /**
     * 取消点赞
     *
     * @param noteId
     * @param userId
     * @return
     */
    void cancelLike(Long noteId, Long userId);

    /**
     * 是否点赞
     *
     * @param noteId
     * @param userId
     * @return
     */
    boolean isLike(Long noteId, Long userId);

    /**
     * 统计用户的点赞数
     *
     * @param userId
     * @return
     */
    int countByUserId(Long userId);
}
