package com.example.sens.common.schedule;

import com.example.sens.mapper.DishMapper;
import com.example.sens.mapper.StoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 * 系统定时任务
 */
@Component
public class SystemSchedule {


    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private StoreMapper storeMapper;


    @Scheduled(fixedRate = 10000)
    @Transactional(rollbackFor = Exception.class)
    public void updateCommentStatus() {
        // 更新菜品评价
        dishMapper.updateDishScore();

        // 更新店铺评价
        storeMapper.updateStoreScore();
    }

}
