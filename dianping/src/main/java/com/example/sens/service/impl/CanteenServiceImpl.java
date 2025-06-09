package com.example.sens.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sens.entity.Canteen;
import com.example.sens.mapper.CanteenMapper;
import com.example.sens.service.CanteenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 食堂Service实现类
 */
@Service
public class CanteenServiceImpl extends ServiceImpl<CanteenMapper, Canteen> implements CanteenService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void incrementViewSize(Long id) {
        Canteen canteen = this.getById(id);
        if (canteen != null) {
            canteen.setViewSize(canteen.getViewSize() + 1);
            this.updateById(canteen);
        }
    }
} 