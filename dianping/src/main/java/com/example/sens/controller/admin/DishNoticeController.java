package com.example.sens.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sens.common.constant.CommonConstant;
import com.liuyanzhao.yztool.common.util.PageUtil;
import com.liuyanzhao.yztool.common.vo.JsonResult;
import com.example.sens.controller.common.BaseController;
import com.example.sens.entity.DishNotice;
import com.example.sens.entity.Store;
import com.example.sens.service.DishNoticeService;
import com.example.sens.service.StoreService;
import com.example.sens.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
@Controller("adminDishNoticeController")
@RequestMapping("/admin/dishNotice")
public class DishNoticeController extends BaseController {

    @Autowired
    private DishNoticeService dishNoticeService;

    @Autowired
    private UserService userService;

    @Autowired
    private StoreService storeService;


    /**
     * 分页列表
     */
    @GetMapping({"", "/list"})
    public String list(
            @RequestParam(value = "type", defaultValue = "") String type,
            @RequestParam(value = "storeId", defaultValue = "-1") Long storeId,
            @RequestParam(value = "keywords", defaultValue = "") String keywords,
            @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "order", defaultValue = "desc") String order,
            Model model) {
        Page page = PageUtil.initMpPage(pageNumber, pageSize, sort, order);
        LambdaQueryWrapper<DishNotice> queryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotEmpty(keywords)) {
            queryWrapper.like(DishNotice::getName, keywords);
        }
        if (StringUtils.isNotEmpty(type)&& !"-1".equals(type)) {
            queryWrapper.eq(DishNotice::getType, type);
        }
        if (storeId != null && storeId != -1) {
            queryWrapper.eq(DishNotice::getStoreId, storeId);
        }
        if(!loginUserIsAdmin()) {
            // 只能看到自己店铺的评价
            LambdaQueryWrapper<Store> queryWrapper2 = new LambdaQueryWrapper<>();
            if (!loginUserIsAdmin()) {
                queryWrapper2.eq(Store::getBusinessUserId, getLoginUserId());
            }
            List<Store> storeList = storeService.list(queryWrapper2);
            if (storeList.size() > 0) {
                List<Long> storeIds = storeList.stream().map(p -> p.getId()).collect(Collectors.toList());
                queryWrapper.in(DishNotice::getStoreId, storeIds);
            } else {
                queryWrapper.in(DishNotice::getStoreId, -1);
            }
        }

        IPage<DishNotice> dishNotices = dishNoticeService.page(page, queryWrapper);
        for (DishNotice dishNotice : dishNotices.getRecords()) {
            Store store = storeService.getById(dishNotice.getStoreId());
            dishNotice.setStore(store == null ? new Store() : store);
        }
        model.addAttribute("dataList", dishNotices.getRecords());
        model.addAttribute("pageInfo", PageUtil.convertPageVo(page));
        model.addAttribute("keywords", keywords);
        model.addAttribute("typeList", CommonConstant.DISH_NOTICE_TYPE);

        LambdaQueryWrapper<Store> queryWrapper2 = new LambdaQueryWrapper<>();
        if (!loginUserIsAdmin()) {
            queryWrapper2.eq(Store::getBusinessUserId, getLoginUserId());
        }
        model.addAttribute("storeList", storeService.list(queryWrapper2));

        model.addAttribute("type", type);
        model.addAttribute("storeId", storeId);
        return "admin/dishNotice/list";
    }

    /**
     * 添加页面
     */
    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("item", new DishNotice());

        model.addAttribute("typeList", CommonConstant.DISH_NOTICE_TYPE);

        LambdaQueryWrapper<Store> queryWrapper = new LambdaQueryWrapper<>();
        if (!loginUserIsAdmin()) {
            queryWrapper.eq(Store::getBusinessUserId, getLoginUserId());
        }
        model.addAttribute("storeList", storeService.list(queryWrapper));
        return "admin/dishNotice/edit";
    }


    /**
     * 添加页面
     */
    @GetMapping("/edit")
    public String editPage(@RequestParam Long id, Model model) {
        DishNotice dishNotice = dishNoticeService.getById(id);
        model.addAttribute("item", dishNotice);
        model.addAttribute("typeList", CommonConstant.DISH_NOTICE_TYPE);

        LambdaQueryWrapper<Store> queryWrapper = new LambdaQueryWrapper<>();
        if (!loginUserIsAdmin()) {
            queryWrapper.eq(Store::getBusinessUserId, getLoginUserId());
        }
        model.addAttribute("storeList", storeService.list(queryWrapper));
        return "admin/dishNotice/edit";
    }


    /**
     * 详情页面
     */
    @GetMapping("/detail")
    public String detailPage(@RequestParam Long id, Model model) {
        DishNotice dishNotice = dishNoticeService.getById(id);
        model.addAttribute("item", dishNotice);
        model.addAttribute("typeList", CommonConstant.DISH_NOTICE_TYPE);
        return "admin/dishNotice/detail";
    }


    /**
     * 保存或添加提交
     */
    @PostMapping(value = "/save")
    @ResponseBody
    public JsonResult saveDishNotice(DishNotice dishNotice) {
        if(dishNotice.getId() == null) {
            dishNotice.setCreateTime(new Date());
        }
        dishNoticeService.saveOrUpdate(dishNotice);
        return JsonResult.success("保存成功");
    }


    /**
     * 删除
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public JsonResult removeDishNotice(@RequestParam("id") Long dishNoticeId) {
        dishNoticeService.removeById(dishNoticeId);
        return JsonResult.success("删除成功");
    }


    /**
     * 批量删除
     */
    @PostMapping(value = "/batchDelete")
    @ResponseBody
    public JsonResult batchDelete(@RequestParam("ids") List<Long> ids) {
        if (ids == null || ids.size() == 0 || ids.size() >= 100) {
            return JsonResult.error("参数不合法!");
        }
        dishNoticeService.removeByIds(ids);
        return JsonResult.success("删除成功");
    }
}