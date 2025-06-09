package com.example.sens.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sens.entity.Store;
import com.example.sens.mapper.StoreMapper;
import lombok.extern.slf4j.Slf4j;
import com.example.sens.service.StoreService;
import org.springframework.stereotype.Service;

/**
 * 服务接口实现
 *
 * @author saysky
 * @description
 * @since 2024-02-18 12:39:38
 */
@Slf4j
@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService {

}