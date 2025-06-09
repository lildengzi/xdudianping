package com.example.sens.controller.home;

import com.example.sens.controller.common.BaseController;
import com.example.sens.entity.Dish;
import com.example.sens.entity.Mark;
import com.example.sens.entity.Store;
import com.example.sens.entity.User;
import com.example.sens.service.DishService;
import com.example.sens.service.MarkService;
import com.example.sens.service.StoreService;
import com.example.sens.service.UserService;
import com.liuyanzhao.yztool.common.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 购物车
 *
 * @author saysky
 * @description
 * @since 2024-05-5 12:39:38
 */
@Slf4j
@Controller("cartController")
@RequestMapping("/cart")
public class CartController extends BaseController {

    @Autowired
    private DishService dishService;


    /**
     * 保存菜品收藏
     *
     * @param id
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public JsonResult save(Long id, HttpSession session) {
        Dish dish = dishService.getById(id);
        if (dish == null) {
            return JsonResult.error("菜品不存在");
        }

        List<Long> dishIds = (List<Long>) session.getAttribute("cart");
        if (CollectionUtils.isEmpty(dishIds)) {
            dishIds = new ArrayList<>();
        }
        dishIds.add(0, id);
        session.setAttribute("cart", dishIds);
        return JsonResult.success("加入成功");
    }


    /**
     * 取消菜品收藏
     *
     * @param id
     * @return
     */
    @PostMapping("/cancel")
    @ResponseBody
    public JsonResult cancelStoreMark(Long id, HttpSession session) {
        List<Long> dishIds = (List<Long>) session.getAttribute("cart");
        if (CollectionUtils.isEmpty(dishIds)) {
            dishIds = new ArrayList<>();
        } else {
            dishIds.remove(id);
        }
        session.setAttribute("cart", dishIds);
        return JsonResult.success("取消成功");
    }

    @GetMapping("/list")
    @ResponseBody
    public JsonResult list(HttpSession session) {
        List<Long> dishIds = (List<Long>) session.getAttribute("cart");
        List<Dish> result = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(dishIds)) {
            for (Long id : dishIds) {
                Dish dish = dishService.getById(id);
                if (dish != null) {
                    result.add(dish);
                }
            }
        }
        return JsonResult.success("查询成功", result);
    }

}