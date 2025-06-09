package com.example.sens.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sens.entity.DishNotice;
import com.example.sens.mapper.DishNoticeMapper;
import lombok.extern.slf4j.Slf4j;
import com.example.sens.service.DishNoticeService;
import org.springframework.stereotype.Service;

/**
 * 服务接口实现
 *
 * @author saysky
 * @since 2024-02-18 12:39:38
 * @description 
 */
@Slf4j
@Service
public class DishNoticeServiceImpl extends ServiceImpl<DishNoticeMapper, DishNotice> implements DishNoticeService {

}