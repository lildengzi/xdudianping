package com.example.sens.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.sens.entity.Dish;

import java.util.List;

/**
 * 服务接口
 *
 * @author saysky
 * @since 2024-02-18 12:39:38
 * @description 
 */
public interface DishService extends IService<Dish> {

    /**
     * 浏览量
     *
     * @return
     */
    List<Dish> getDishViewRank();

    /**
     * 评分
     *
     * @return
     */
    List<Dish> getDishScoreRank();

    /**
     * 查询最新高分菜品
     *
     * @param limit
     * @return
     */
    List<Dish> getLatestTopRankDish(Integer limit);

}
