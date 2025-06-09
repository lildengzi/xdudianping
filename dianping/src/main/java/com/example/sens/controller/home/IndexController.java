package com.example.sens.controller.home;

import cn.hutool.http.HtmlUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.sens.common.constant.CommonConstant;
import com.example.sens.common.enums.CommentStatusEnum;
import com.example.sens.controller.common.BaseController;
import com.example.sens.entity.*;
import com.example.sens.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 首页控制器
 */
@Controller
public class IndexController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private DishService dishService;

    @Autowired
    private DishCommentService dishCommentService;

    @Autowired
    private StoreCommentService storeCommentService;

    @Autowired
    private CanteenService canteenService;

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        // 获取食堂列表
        LambdaQueryWrapper<Canteen> canteenQueryWrapper = new LambdaQueryWrapper<>();
        canteenQueryWrapper.orderByDesc(Canteen::getViewSize);
        List<Canteen> canteens = canteenService.list(canteenQueryWrapper);
        model.addAttribute("canteens", canteens);

        // 优质店铺
        LambdaQueryWrapper<Store> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Store::getAdRecommend, 1);
        queryWrapper.last("limit 6");
        List<Store> recommendStoreList = storeService.list(queryWrapper);
        for (Store item : recommendStoreList) {
            // 店铺评论数
            LambdaQueryWrapper<StoreComment> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(StoreComment::getStoreId, item.getId());
            item.setCommentSize(storeCommentService.count(queryWrapper1));
        }
        model.addAttribute("recommendStoreList", recommendStoreList);

        // 推荐菜品
        LambdaQueryWrapper<Dish> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(Dish::getAdRecommend, 1);
        queryWrapper2.last("limit 6");
        List<Dish> recommendDishList = dishService.list(queryWrapper2);
        for (Dish item : recommendDishList) {
            // 店铺评论数
            LambdaQueryWrapper<DishComment> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(DishComment::getDishId, item.getId());
            item.setCommentSize(dishCommentService.count(queryWrapper1));
        }
        model.addAttribute("recommendDishList", recommendDishList);


        // 最新菜品评价
        LambdaQueryWrapper<DishComment> queryWrapper3 = new LambdaQueryWrapper<>();
        queryWrapper3.eq(DishComment::getPid, 0);
        queryWrapper3.ne(DishComment::getStatus, CommentStatusEnum.NOT_CHECK.getValue());
        queryWrapper3.last("limit 6");
        queryWrapper3.orderByDesc(DishComment::getCreateTime);
        List<DishComment> dishCommentList = dishCommentService.list(queryWrapper3);
        for (DishComment item : dishCommentList) {
            // 菜品
            Dish dish = dishService.getById(item.getDishId());
            item.setDish(dish == null ? new Dish() : dish);

            // 用户
            User user = userService.getById(item.getUserId());
            item.setUser(user == null ? new User() : user);

            // 摘要
            String summaryText = HtmlUtil.cleanHtmlTag(item.getContent());
            if (summaryText.length() > CommonConstant.SUMMARY_LENGTH) {
                summaryText = summaryText.substring(0, CommonConstant.SUMMARY_LENGTH) + "...";
            }
            item.setContent(summaryText);
        }
        model.addAttribute("dishCommentList", dishCommentList);


        // 最新店铺评价
        LambdaQueryWrapper<StoreComment> queryWrapper4 = new LambdaQueryWrapper<>();
        queryWrapper4.eq(StoreComment::getPid, 0);
        queryWrapper4.ne(StoreComment::getStatus, CommentStatusEnum.NOT_CHECK.getValue());
        queryWrapper4.last("limit 6");
        queryWrapper4.orderByDesc(StoreComment::getCreateTime);
        List<StoreComment> storeCommentList = storeCommentService.list(queryWrapper4);
        for (StoreComment item : storeCommentList) {
            // 菜品
            Store store = storeService.getById(item.getStoreId());
            item.setStore(store == null ? new Store() : store);

            // 用户
            User user = userService.getById(item.getUserId());
            item.setUser(user == null ? new User() : user);

            // 摘要
            String summaryText = HtmlUtil.cleanHtmlTag(item.getContent());
            if (summaryText.length() > CommonConstant.SUMMARY_LENGTH) {
                summaryText = summaryText.substring(0, CommonConstant.SUMMARY_LENGTH) + "...";
            }
            item.setContent(summaryText);
        }
        model.addAttribute("storeCommentList", storeCommentList);


        // 猜你喜欢，找评分最高的菜品
        List<Dish> latestTopRankDish = dishService.getLatestTopRankDish(6);
        for (Dish item : latestTopRankDish) {
            // 店铺评论数
            LambdaQueryWrapper<DishComment> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(DishComment::getDishId, item.getId());
            item.setCommentSize(dishCommentService.count(queryWrapper1));
        }
        model.addAttribute("latestTopRankDish", latestTopRankDish);

        return "home/index";
    }



    @GetMapping("/commentSuccess")
    public String commentSuccess() {
        return "home/commentSuccess";
    }


}
