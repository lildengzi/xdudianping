package com.example.sens.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.sens.common.vo.RankVO;
import com.example.sens.entity.DishComment;

import java.util.List;

/**
 * 服务接口
 *
 * @author saysky
 * @since 2024-02-18 12:39:38
 * @description 
 */
public interface DishCommentService extends IService<DishComment> {


    /**
     * 好评排名
     *
     * @return
     */
    List<RankVO> getDishScoreRank();


    /**
     * 根据菜品id获取分数
     * @param distId
     * @return
     */
    Integer getScoreByDishId(Long distId);
}
