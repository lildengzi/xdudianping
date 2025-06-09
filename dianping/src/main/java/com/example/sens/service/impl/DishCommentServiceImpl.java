package com.example.sens.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sens.common.vo.RankVO;
import com.example.sens.entity.DishComment;
import com.example.sens.mapper.DishCommentMapper;
import lombok.extern.slf4j.Slf4j;
import com.example.sens.service.DishCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务接口实现
 *
 * @author saysky
 * @description
 * @since 2024-02-18 12:39:38
 */
@Slf4j
@Service
public class DishCommentServiceImpl extends ServiceImpl<DishCommentMapper, DishComment> implements DishCommentService {

    @Autowired
    private DishCommentMapper dishCommentMapper;

    @Override
    public List<RankVO> getDishScoreRank() {
        return dishCommentMapper.getDishScoreRank();
    }

    @Override
    public Integer getScoreByDishId(Long distId) {
        return dishCommentMapper.getScoreByDishId(distId);
    }
}