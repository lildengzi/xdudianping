package com.example.sens.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuyanzhao.yztool.common.vo.JsonResult;
import com.example.sens.controller.common.BaseController;
import com.example.sens.entity.Dish;
import com.example.sens.entity.DishComment;
import com.example.sens.entity.Store;
import com.example.sens.service.DishCommentService;
import com.example.sens.service.DishService;
import com.example.sens.service.StoreService;
import com.example.sens.service.UserService;
import com.liuyanzhao.yztool.common.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 服务控制器
 *
 * @author saysky
 * @description
 * @since 2024-02-18 12:39:38
 */
@Slf4j
@Controller("adminDishController")
@RequestMapping("/admin/dish")
public class DishController extends BaseController {

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
    @GetMapping({"", "/list"})
    public String list(
            @RequestParam(value = "keywords", defaultValue = "") String keywords,
            @RequestParam(value = "storeId", defaultValue = "-1") Long storeId,
            @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "order", defaultValue = "desc") String order,
            Model model) {
        if (!loginUserIsAdmin() && !loginUserIsBusiness()) {
            return this.renderNotAllowAccess();
        }

        LambdaQueryWrapper<Store> queryWrapper2 = new LambdaQueryWrapper<>();
        if (!loginUserIsAdmin()) {
            queryWrapper2.eq(Store::getBusinessUserId, getLoginUserId());
        }
        List<Store> storeList = storeService.list(queryWrapper2);


        Page page = PageUtil.initMpPage(pageNumber, pageSize, sort, order);
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotEmpty(keywords)) {
            queryWrapper.like(Dish::getName, keywords);
        }
        if (storeId != null && storeId != -1) {
            queryWrapper.eq(Dish::getStoreId, storeId);
        }
        if (!loginUserIsAdmin()) {
            if (storeList.size() > 0) {
                List<Long> storeIds = storeList.stream().map(p -> p.getId()).collect(Collectors.toList());
                queryWrapper.in(Dish::getStoreId, storeIds);
            } else {
                queryWrapper.in(Dish::getStoreId, -1);
            }
        }

        IPage<Dish> dishs = dishService.page(page, queryWrapper);
        for (Dish dish : dishs.getRecords()) {
            Store store = storeService.getById(dish.getStoreId());
            dish.setStore(store == null ? new Store() : store);
        }

        model.addAttribute("dataList", dishs.getRecords());
        model.addAttribute("pageInfo", PageUtil.convertPageVo(page));
        model.addAttribute("keywords", keywords);
        model.addAttribute("storeId", storeId);

        model.addAttribute("isAdmin", loginUserIsAdmin());

        model.addAttribute("storeList", storeList);
        return "admin/dish/list";
    }

    /**
     * 添加页面
     */
    @GetMapping("/add")
    public String addPage(Model model) {
        if (!loginUserIsAdmin() && !loginUserIsBusiness()) {
            return this.renderNotAllowAccess();
        }
        model.addAttribute("item", new Dish());

        LambdaQueryWrapper<Store> queryWrapper = new LambdaQueryWrapper<>();
        if (!loginUserIsAdmin()) {
            queryWrapper.eq(Store::getBusinessUserId, getLoginUserId());
        }
        model.addAttribute("storeList", storeService.list(queryWrapper));
        return "admin/dish/edit";
    }


    /**
     * 添加页面
     */
    @GetMapping("/edit")
    public String editPage(@RequestParam Long id, Model model) {
        if (!loginUserIsAdmin() && !loginUserIsBusiness()) {
            return this.renderNotAllowAccess();
        }
        Dish dish = dishService.getById(id);
        model.addAttribute("item", dish);


        LambdaQueryWrapper<Store> queryWrapper = new LambdaQueryWrapper<>();
        if (!loginUserIsAdmin()) {
            queryWrapper.eq(Store::getBusinessUserId, getLoginUserId());
        }
        model.addAttribute("storeList", storeService.list(queryWrapper));
        return "admin/dish/edit";
    }


    /**
     * 详情页面
     */
    @GetMapping("/detail")
    public String detailPage(@RequestParam Long id, Model model) {
        if (!loginUserIsAdmin() && !loginUserIsBusiness()) {
            return this.renderNotAllowAccess();
        }

        Dish dish = dishService.getById(id);
        model.addAttribute("item", dish);

        LambdaQueryWrapper<Store> queryWrapper = new LambdaQueryWrapper<>();
        if (!loginUserIsAdmin()) {
            queryWrapper.eq(Store::getBusinessUserId, getLoginUserId());
        }
        model.addAttribute("storeList", storeService.list(queryWrapper));
        return "admin/dish/detail";
    }


    /**
     * 保存或添加提交
     */
    @PostMapping(value = "/save")
    @ResponseBody
    public JsonResult saveDish(Dish dish) {
        if (!loginUserIsAdmin() && !loginUserIsBusiness()) {
            return JsonResult.error("没有权限");
        }

        if (dish.getId() == null) {
            dish.setViewSize(0);
        }
        if(dish.getStoreId() == null) {
            return JsonResult.error("请选择店铺");
        }

        dishService.saveOrUpdate(dish);
        return JsonResult.success("保存成功");
    }


    /**
     * 推荐菜品
     */
    @PostMapping(value = "/openDishRecommend")
    @ResponseBody
    public JsonResult openDishRecommend(Long id) {
        Dish dish = dishService.getById(id);
        if (dish == null) {
            return JsonResult.error("菜品不存在");
        }
        dish.setAdRecommend(1);
        dishService.updateById(dish);
        return JsonResult.success("推荐成功");
    }

    /**
     * 关闭推荐
     */
    @PostMapping(value = "/closeDishRecommend")
    @ResponseBody
    public JsonResult closeDishRecommend(Long id) {
        Dish dish = dishService.getById(id);
        if (dish == null) {
            return JsonResult.error("菜品不存在");
        }
        dish.setAdRecommend(0);
        dishService.updateById(dish);
        return JsonResult.success("取消推荐成功");
    }


    /**
     * 删除
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public JsonResult removeDish(@RequestParam("id") Long dishId) {
        if (!loginUserIsAdmin() && !loginUserIsBusiness()) {
            return JsonResult.error("没有权限");
        }
        dishService.removeById(dishId);

        // 删除对应评价
        LambdaQueryWrapper<DishComment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishComment::getDishId, dishId);
        dishCommentService.remove(queryWrapper);
        return JsonResult.success("删除成功");
    }


    /**
     * 批量删除
     */
    @PostMapping(value = "/batchDelete")
    @ResponseBody
    public JsonResult batchDelete(@RequestParam("ids") List<Long> ids) {
        if (!loginUserIsAdmin() && !loginUserIsBusiness()) {
            return JsonResult.error("没有权限");
        }
        if (ids == null || ids.size() == 0 || ids.size() >= 100) {
            return JsonResult.error("参数不合法!");
        }
        for (Long id : ids) {
            // 删除对应评价
            LambdaQueryWrapper<DishComment> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(DishComment::getDishId, id);
            dishCommentService.remove(queryWrapper);

            dishService.removeById(id);
        }

        return JsonResult.success("删除成功");
    }
}