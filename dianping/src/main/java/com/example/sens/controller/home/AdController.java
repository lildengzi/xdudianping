package com.example.sens.controller.home;

import cn.hutool.http.HtmlUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sens.common.constant.CommonConstant;
import com.liuyanzhao.yztool.common.util.PageUtil;
import com.example.sens.controller.common.BaseController;
import com.example.sens.entity.Dish;
import com.example.sens.entity.Store;
import com.example.sens.service.DishCommentService;
import com.example.sens.service.DishService;
import com.example.sens.service.StoreService;
import com.example.sens.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 服务控制器
 *
 * @author saysky
 * @description
 * @since 2024-02-18 12:39:38
 */
@Slf4j
@Controller("adController")
@RequestMapping("/ad")
public class AdController extends BaseController {

    @Autowired
    private DishService dishService;

    @Autowired
    private UserService userService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private DishCommentService dishCommentService;

    /**
     * 分页列表
     */
    @GetMapping("/dish")
    public String dishList(
            @RequestParam(value = "keywords", defaultValue = "") String keywords,
            @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sort", defaultValue = "score") String sort,
            @RequestParam(value = "order", defaultValue = "desc") String order,
            Model model) {


        Page page = PageUtil.initMpPage(pageNumber, pageSize, sort, order);
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotEmpty(keywords)) {
            queryWrapper.like(Dish::getName, keywords);
        }
        queryWrapper.eq(Dish::getAdRecommend, 1);

        IPage<Dish> dishs = dishService.page(page, queryWrapper);
        for (Dish dish : dishs.getRecords()) {
            Store store = storeService.getById(dish.getStoreId());
            dish.setStore(store == null ? new Store() : store);

            // 摘要
            String summaryText = HtmlUtil.cleanHtmlTag(dish.getContent());
            if (summaryText.length() > CommonConstant.SUMMARY_LENGTH) {
                summaryText = summaryText.substring(0, CommonConstant.SUMMARY_LENGTH) + "...";
            }
            dish.setContent(summaryText);
        }

        model.addAttribute("dataList", dishs.getRecords());
        model.addAttribute("pageInfo", PageUtil.convertPageVo(page));
        model.addAttribute("keywords", keywords);
        model.addAttribute("title", StringUtils.isNotEmpty(keywords) ? "搜索" + keywords : "推荐菜品");
        return "home/dish/list";
    }

    /**
     * 分页列表
     */
    @GetMapping("/store")
    public String storeList(
            @RequestParam(value = "keywords", defaultValue = "") String keywords,
            @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sort", defaultValue = "score") String sort,
            @RequestParam(value = "order", defaultValue = "desc") String order,
            Model model) {


        Page page = PageUtil.initMpPage(pageNumber, pageSize, sort, order);
        LambdaQueryWrapper<Store> queryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotEmpty(keywords)) {
            queryWrapper.like(Store::getName, keywords);
        }

        queryWrapper.eq(Store::getAdRecommend, 1);

        IPage<Store> stores = storeService.page(page, queryWrapper);

        model.addAttribute("dataList", stores.getRecords());
        model.addAttribute("pageInfo", PageUtil.convertPageVo(page));
        model.addAttribute("keywords", keywords);
        model.addAttribute("title", StringUtils.isNotEmpty(keywords) ? "搜索" + keywords : "店铺列表");
        return "home/store/list";
    }
}