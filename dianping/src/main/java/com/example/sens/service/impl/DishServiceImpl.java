package com.example.sens.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sens.entity.Dish;
import com.example.sens.entity.DishComment;
import com.example.sens.mapper.DishCommentMapper;
import com.example.sens.mapper.DishMapper;
import lombok.extern.slf4j.Slf4j;
import com.example.sens.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 服务接口实现
 *
 * @author saysky
 * @description
 * @since 2024-02-18 12:39:38
 */
@Slf4j
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishCommentMapper dishCommentMapper;

    @Override
    public List<Dish> getDishViewRank() {
        return dishMapper.getDishViewRank();
    }

    @Override
    public List<Dish> getDishScoreRank() {
        return dishMapper.getDishScoreRank();
    }

    @Override
    public List<Dish> getLatestTopRankDish(Integer limit) {
        // 从内存中实现
        List<Dish> dishList = new ArrayList<>();
        LambdaQueryWrapper<DishComment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishComment::getStatus, 1);  // 评论状态
        queryWrapper.orderByDesc(DishComment::getScore).orderByDesc(DishComment::getCreateTime);
        queryWrapper.last("limit 100");  // 限制查询数量
        List<DishComment> dishCommentList = dishCommentMapper.selectList(queryWrapper);
        Set<Long> dishIds = new HashSet<>();


        for (DishComment dishComment : dishCommentList) {
            if (dishIds.contains(dishComment.getDishId())) {
                continue;
            }
            Dish dish = dishMapper.selectById(dishComment.getDishId());
            if (dish != null) {
                dishList.add(dish);
                dishIds.add(dishComment.getDishId());
            }
            if (dishList.size() >= limit) {
                break;
            }
        }
        return dishList;
    }
}