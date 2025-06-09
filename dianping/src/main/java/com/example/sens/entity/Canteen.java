package com.example.sens.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.sens.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 食堂实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("biz_canteen")
public class Canteen extends BaseEntity {

    /**
     * 食堂名称
     */
    private String name;

    /**
     * 食堂图片
     */
    private String photo;

    /**
     * 食堂简介
     */
    private String description;

    /**
     * 营业时间
     */
    private String businessTime;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 楼层数
     */
    private Integer floorCount;

    /**
     * 访问量
     */
    private Integer viewSize;

    /**
     * 状态：1营业，0休息
     */
    private Integer status;
} 