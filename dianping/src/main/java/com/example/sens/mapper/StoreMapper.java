package com.example.sens.mapper;

import com.example.sens.entity.Store;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * (biz_store)数据Mapper
 *
 * @author saysky
 * @since 2024-02-18 12:39:38
 * @description 
*/
@Mapper
public interface StoreMapper extends BaseMapper<Store> {


    /**
     * 更新店铺评价
     * @return
     */
    int updateStoreScore();
}
