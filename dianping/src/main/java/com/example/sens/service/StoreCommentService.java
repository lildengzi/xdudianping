package com.example.sens.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.sens.common.vo.RankVO;
import com.example.sens.entity.StoreComment;

import java.util.List;

/**
 * 服务接口
 *
 * @author saysky
 * @since 2024-02-18 12:39:38
 * @description 
 */
public interface StoreCommentService extends IService<StoreComment> {

    /**
     * 口味评分排名
     *
     * @return
     */
    List<RankVO> getStoreTasteRank();

    /**
     * 环境评分排名
     *
     * @return
     */
    List<RankVO> getStoreEnvironmentRank();

    /**
     * 服务评分排名
     *
     * @return
     */
    List<RankVO> getStoreServiceRank();

}
