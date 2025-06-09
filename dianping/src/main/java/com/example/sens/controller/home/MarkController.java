package com.example.sens.controller.home;

import com.liuyanzhao.yztool.common.vo.JsonResult;
import com.example.sens.controller.common.BaseController;
import com.example.sens.entity.*;
import com.example.sens.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 服务控制器
 *
 * @author saysky
 * @description
 * @since 2024-02-18 12:39:38
 */
@Slf4j
@Controller("markController")
@RequestMapping("/mark")
public class MarkController extends BaseController {

    @Autowired
    private MarkService markService;

    @Autowired
    private UserService userService;

    @Autowired
    private DishService dishService;

    @Autowired
    private StoreService storeService;


    /**
     * 保存菜品收藏
     *
     * @param id
     * @return
     */
    @PostMapping("/saveStoreMark")
    @ResponseBody
    public JsonResult saveStoreMark(Long id) {
        Store store = storeService.getById(id);
        if (store == null) {
            return JsonResult.error("店铺不存在");
        }
        User loginUser = getLoginUser();
        if (loginUser == null) {
            return JsonResult.error("用户未登录");
        }
        Mark mark = new Mark();
        mark.setCreateTime(new Date());
        mark.setUserId(loginUser.getId());
        mark.setMainType(1);
        mark.setMainId(id);
        mark.setMainName(store.getName());
        markService.save(mark);
        return JsonResult.success("收藏成功");
    }
    
    /**
     * 保存菜品收藏
     *
     * @param id
     * @return
     */
    @PostMapping("/saveDishMark")
    @ResponseBody
    public JsonResult saveDishMark(Long id) {
        Dish dish = dishService.getById(id);
        if (dish == null) {
            return JsonResult.error("菜品不存在");
        }
        User loginUser = getLoginUser();
        if (loginUser == null) {
            return JsonResult.error("用户未登录");
        }
        Mark mark = new Mark();
        mark.setCreateTime(new Date());
        mark.setUserId(loginUser.getId());
        mark.setMainType(2);
        mark.setMainId(id);
        mark.setMainName(dish.getName());
        markService.save(mark);
        return JsonResult.success("收藏成功");
    }



    /**
     * 取消菜品收藏
     *
     * @param id
     * @return
     */
    @PostMapping("/cancelStoreMark")
    @ResponseBody
    public JsonResult cancelStoreMark(Long id) {
        Store store = storeService.getById(id);
        if (store == null) {
            return JsonResult.error("店铺不存在");
        }
        User loginUser = getLoginUser();
        if (loginUser == null) {
            return JsonResult.error("用户未登录");
        }
        markService.deleteMark(loginUser.getId(), 1, id);
        return JsonResult.success("取消收藏成功");
    }

    /**
     * 取消菜品收藏
     *
     * @param id
     * @return
     */
    @PostMapping("/cancelDishMark")
    @ResponseBody
    public JsonResult cancelDishMark(Long id) {
        Dish dish = dishService.getById(id);
        if (dish == null) {
            return JsonResult.error("菜品不存在");
        }
        User loginUser = getLoginUser();
        if (loginUser == null) {
            return JsonResult.error("用户未登录");
        }
        markService.deleteMark(loginUser.getId(), 2, id);
        return JsonResult.success("取消收藏成功");
    }
}