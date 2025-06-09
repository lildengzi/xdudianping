package com.example.sens.common.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.example.sens.entity.Dish;
import com.example.sens.entity.Store;
import lombok.Data;

/**
 * 排行榜VO
 */
@Data
public class RankVO {

    /**
     * 店铺id
     */
    private Long storeId;


    /**
     * 菜品id
     */
    private Long dishId;

    /**
     * 总分
     */
    private Long totalScore;

    /**
     * 平均分
     */
    private Double avgScore;

    /**
     * 评价数量
     */
    private Integer count;

    @TableField(exist = false)
    private Store store;

    @TableField(exist = false)
    private Dish dish;
}
