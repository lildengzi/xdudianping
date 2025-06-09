package com.example.sens.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.sens.entity.Mark;

/**
 * 服务接口
 *
 * @author saysky
 * @description
 * @since 2024-02-18 12:39:38
 */
public interface MarkService extends IService<Mark> {

    /**
     * 是否已收藏
     * @param userId
     * @param markType
     * @param mainId
     * @return
     */
    boolean hasMark(Long userId, Integer markType, Long mainId);

    /**
     * 添加收藏
     * @param userId
     * @param markType
     * @param mainId
     */
    void saveMark(Long userId, Integer markType, String mainName, Long mainId);

    /**
     * 取消收藏
     * @param userId
     * @param markType
     * @param mainId
     */
    void deleteMark(Long userId, Integer markType, Long mainId);

    /**
     * 统计用户的收藏数
     * @param userId
     * @param markType
     * @return
     */
    int countByUserId(Long userId, Integer markType);
}
