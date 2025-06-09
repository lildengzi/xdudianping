package com.example.sens.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sens.controller.common.BaseController;
import com.example.sens.entity.*;
import com.example.sens.service.DishService;
import com.example.sens.service.StoreService;
import com.example.sens.service.UserService;
import com.liuyanzhao.yztool.common.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <pre>
 *     营销管理
 * </pre>
 */
@Slf4j
@Controller("adminAdController")
@RequestMapping(value = "/admin/ad")
public class AdController extends BaseController {

    @Autowired
    private DishService dishService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private UserService userService;

    /**
     * 优质店铺
     */
    @GetMapping("/store")
    public String storeList(
            @RequestParam(value = "keywords", defaultValue = "") String keywords,
            @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "order", defaultValue = "desc") String order,
            Model model) {


        LambdaQueryWrapper<Store> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Store::getAdRecommend, 1);

        if (StringUtils.isNotEmpty(keywords)) {
            queryWrapper.like(Store::getName, keywords);
        }
        Page page = PageUtil.initMpPage(pageNumber, pageSize, sort, order);
        IPage<Store> storeIPage = storeService.page(page, queryWrapper);
        for (Store store : storeIPage.getRecords()) {
            User user = userService.getById(store.getBusinessUserId());
            store.setUser(user == null ? new User() : user);
        }
        model.addAttribute("dataList", storeIPage.getRecords());
        model.addAttribute("pageInfo", PageUtil.convertPageVo(page));
        model.addAttribute("keywords", keywords);
        return "admin/ad/storeList";
    }


    /**
     * 推荐菜品
     */
    @GetMapping("/dish")
    public String dishList(
            @RequestParam(value = "keywords", defaultValue = "") String keywords,
            @RequestParam(value = "storeId", defaultValue = "-1") Long storeId,
            @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "order", defaultValue = "desc") String order,
            Model model) {

        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Dish::getAdRecommend, 1);
        if (storeId != null && storeId != -1) {
            queryWrapper.eq(Dish::getStoreId, storeId);
        }
        if (StringUtils.isNotEmpty(keywords)) {
            queryWrapper.like(Dish::getName, keywords);
        }


        Page page = PageUtil.initMpPage(pageNumber, pageSize, sort, order);
        IPage<Dish> dishIPage = dishService.page(page, queryWrapper);
        for (Dish dish : dishIPage.getRecords()) {
            Store store = storeService.getById(dish.getStoreId());
            dish.setStore(store == null ? new Store() : store);
        }
        model.addAttribute("dataList", dishIPage.getRecords());
        model.addAttribute("pageInfo", PageUtil.convertPageVo(page));

        LambdaQueryWrapper<Store> queryWrapper2 = new LambdaQueryWrapper<>();
        if (!loginUserIsAdmin()) {
            queryWrapper2.eq(Store::getBusinessUserId, getLoginUserId());
        }
        List<Store> storeList = storeService.list(queryWrapper2);
        model.addAttribute("storeList", storeList);
        model.addAttribute("storeId", storeId);
        model.addAttribute("keywords", keywords);
        return "admin/ad/dishList";
    }


}
