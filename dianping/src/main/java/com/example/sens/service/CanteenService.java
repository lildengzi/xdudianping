package com.example.sens.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.sens.entity.Canteen;

/**
 * 食堂Service接口
 */
public interface CanteenService extends IService<Canteen> {

    /**
     * 增加访问量
     *
     * @param id 食堂ID
     */
    void incrementViewSize(Long id);
} 