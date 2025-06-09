package com.example.sens.mapper;

import com.example.sens.common.vo.RankVO;
import com.example.sens.entity.StoreComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (biz_store_comment)数据Mapper
 *
 * @author saysky
 * @description
 * @since 2024-02-18 12:39:38
 */
@Mapper
public interface StoreCommentMapper extends BaseMapper<StoreComment> {

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
