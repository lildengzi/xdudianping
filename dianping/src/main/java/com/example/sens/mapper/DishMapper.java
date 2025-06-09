package com.example.sens.mapper;

import com.example.sens.entity.Dish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.sens.entity.DishComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (biz_dish)数据Mapper
 *
 * @author saysky
 * @description
 * @since 2024-02-18 12:39:38
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {


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
     * 更新菜品评价
     *
     * @return
     */
    int updateDishScore();

}
