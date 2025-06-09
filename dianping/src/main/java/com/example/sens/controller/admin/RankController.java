package com.example.sens.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuyanzhao.yztool.common.util.PageUtil;
import com.example.sens.controller.common.BaseController;
import com.example.sens.entity.Dish;
import com.example.sens.entity.Store;
import com.example.sens.entity.User;
import com.example.sens.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <pre>
 *     榜单管理
 * </pre>
 */
@Slf4j
@Controller("adminRankController")
@RequestMapping(value = "/admin/rank")
public class RankController extends BaseController {

    @Autowired
    private DishService dishService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private UserService userService;

    @Autowired
    private StoreCommentService storeCommentService;

    @Autowired
    private DishCommentService dishCommentService;


    /**
     * 店铺排行
     * 分页列表
     */
    @GetMapping("/store")
    public String storeRank(
            @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sort", defaultValue = "score") String sort,
            @RequestParam(value = "order", defaultValue = "desc") String order,
            Model model) {

        Page page = PageUtil.initMpPage(pageNumber, pageSize, sort, order);
        LambdaQueryWrapper<Store> queryWrapper = new LambdaQueryWrapper();

        IPage<Store> pageResult = storeService.page(page, queryWrapper);
        for (Store store : pageResult.getRecords()) {
            User user = userService.getById(store.getBusinessUserId());
            store.setUser(user == null ? new User() : user);
        }

        model.addAttribute("dataList", pageResult.getRecords());
        model.addAttribute("pageInfo", PageUtil.convertPageVo(page));
        model.addAttribute("sort", sort);
        return "admin/rank/storeRank";
    }


    /**
     * 菜品排行
     * 分页列表
     */
    @GetMapping("/dish")
    public String dishRank(
            @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sort", defaultValue = "score") String sort,
            @RequestParam(value = "order", defaultValue = "desc") String order,
            Model model) {

        Page page = PageUtil.initMpPage(pageNumber, pageSize, sort, order);
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper();

        IPage<Dish> pageResult = dishService.page(page, queryWrapper);
        for (Dish dish : pageResult.getRecords()) {
            Store store = storeService.getById(dish.getStoreId());
            dish.setStore(store == null ? new Store() : store);
        }

        model.addAttribute("dataList", pageResult.getRecords());
        model.addAttribute("pageInfo", PageUtil.convertPageVo(page));
        model.addAttribute("sort", sort);
        return "admin/rank/dishRank";
    }


}
