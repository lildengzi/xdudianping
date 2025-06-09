package com.example.sens.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sens.common.vo.RankVO;
import com.example.sens.entity.StoreComment;
import com.example.sens.mapper.StoreCommentMapper;
import lombok.extern.slf4j.Slf4j;
import com.example.sens.service.StoreCommentService;
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
public class StoreCommentServiceImpl extends ServiceImpl<StoreCommentMapper, StoreComment> implements StoreCommentService {

    @Autowired
    private StoreCommentMapper storeCommentMapper;

    @Override
    public List<RankVO> getStoreTasteRank() {
        return storeCommentMapper.getStoreTasteRank();
    }

    @Override
    public List<RankVO> getStoreEnvironmentRank() {
        return storeCommentMapper.getStoreEnvironmentRank();
    }

    @Override
    public List<RankVO> getStoreServiceRank() {
        return storeCommentMapper.getStoreServiceRank();
    }
}