package com.example.sens.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sens.entity.Mark;
import com.example.sens.mapper.MarkMapper;
import lombok.extern.slf4j.Slf4j;
import com.example.sens.service.MarkService;
import org.springframework.stereotype.Service;

import java.util.Date;
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
public class MarkServiceImpl extends ServiceImpl<MarkMapper, Mark> implements MarkService {

    @Override
    public boolean hasMark(Long userId, Integer markType, Long mainId) {
        if(userId == null) {
            return false;
        }
        LambdaQueryWrapper<Mark> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Mark::getUserId, userId);
        queryWrapper.eq(Mark::getMainType, markType);
        queryWrapper.eq(Mark::getMainId, mainId);
        List<Mark> list = this.list(queryWrapper);
        if (list == null || list.size() == 0) {
            return false;
        }
        return true;
    }

    @Override
    public void saveMark(Long userId, Integer markType, String mainName, Long mainId) {
        Mark mark = new Mark();
        mark.setCreateTime(new Date());
        mark.setUserId(userId);
        mark.setMainType(markType);
        mark.setMainId(mainId);
        mark.setMainName(mainName);
        this.save(mark);
    }

    @Override
    public void deleteMark(Long userId, Integer markType, Long mainId) {
        LambdaQueryWrapper<Mark> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Mark::getUserId, userId);
        queryWrapper.eq(Mark::getMainType, markType);
        queryWrapper.eq(Mark::getMainId, mainId);
        this.remove(queryWrapper);
    }

    @Override
    public int countByUserId(Long userId, Integer markType) {
        return baseMapper.countByUserId(userId, markType);
    }
}