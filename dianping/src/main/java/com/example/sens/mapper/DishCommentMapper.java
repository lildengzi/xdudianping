package com.example.sens.mapper;

import com.example.sens.common.vo.RankVO;
import com.example.sens.entity.DishComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (biz_dish_comment)数据Mapper
 *
 * @author saysky
 * @since 2024-02-18 12:39:38
 * @description 
*/
@Mapper
public interface DishCommentMapper extends BaseMapper<DishComment> {


    /**
     * 好评排名
     *
     * @return
     */
    List<RankVO> getDishScoreRank();

    /**
     * 根据菜品id获取分数
     * @param distId
     * @return
     */
    Integer getScoreByDishId(Long distId);

}
