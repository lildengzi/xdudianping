package com.example.sens.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sens.common.enums.RoleEnum;
import com.liuyanzhao.yztool.common.util.PageUtil;
import com.liuyanzhao.yztool.common.vo.JsonResult;
import com.example.sens.controller.common.BaseController;
import com.example.sens.entity.*;
import com.example.sens.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 服务控制器
 *
 * @author saysky
 * @description
 * @since 2024-02-18 12:39:38
 */
@Slf4j
@Controller("adminStoreController")
@RequestMapping("/admin/store")
public class StoreController extends BaseController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private DishService dishService;

    @Autowired
    private StoreCommentService storeCommentService;

    /**
     * 分页列表
     */
    @GetMapping({"", "/list"})
    public String list(
            @RequestParam(value = "keywords", defaultValue = "") String keywords,
            @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sort", defaultValue = "adRecommend") String sort,
            @RequestParam(value = "order", defaultValue = "desc") String order,
            Model model) {


        Page page = PageUtil.initMpPage(pageNumber, pageSize, sort, order);
        LambdaQueryWrapper<Store> queryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotEmpty(keywords)) {
            queryWrapper.like(Store::getName, keywords);
        }

        if (!loginUserIsAdmin()) {
            LambdaQueryWrapper<Store> queryWrapper2 = new LambdaQueryWrapper<>();
            if (!loginUserIsAdmin()) {
                queryWrapper2.eq(Store::getBusinessUserId, getLoginUserId());
            }
            List<Store> storeList = storeService.list(queryWrapper2);

            if (storeList.size() > 0) {
                List<Long> storeIds = storeList.stream().map(p -> p.getId()).collect(Collectors.toList());
                queryWrapper.in(Store::getId, storeIds);
            } else {
                queryWrapper.in(Store::getId, -1);
            }
        }

        IPage<Store> stores = storeService.page(page, queryWrapper);
        for (Store store : stores.getRecords()) {
            User user = userService.getById(store.getBusinessUserId());
            store.setUser(user == null ? new User() : user);
        }
        model.addAttribute("dataList", stores.getRecords());
        model.addAttribute("pageInfo", PageUtil.convertPageVo(page));
        model.addAttribute("keywords", keywords);

        model.addAttribute("isAdmin", loginUserIsAdmin());
        return "admin/store/list";
    }

    /**
     * 添加页面
     */
    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("item", new Store());
        model.addAttribute("isAdmin", loginUserIsAdmin());
        return "admin/store/edit";
    }


    /**
     * 添加页面
     */
    @GetMapping("/edit")
    public String editPage(@RequestParam Long id, Model model) {
        Store store = storeService.getById(id);

        User user = userService.getById(store.getBusinessUserId());
        store.setUser(user == null ? new User() : user);
        model.addAttribute("item", store);

        model.addAttribute("isAdmin", loginUserIsAdmin());
        return "admin/store/edit";
    }


    /**
     * 详情页面
     */
    @GetMapping("/detail")
    public String detailPage(@RequestParam Long id, Model model) {
        Store store = storeService.getById(id);

        User user = userService.getById(store.getBusinessUserId());
        store.setUser(user == null ? new User() : user);
        model.addAttribute("item", store);
        return "admin/store/detail";
    }


    /**
     * 保存或添加提交
     */
    @PostMapping(value = "/save")
    @ResponseBody
    public JsonResult saveStore(Store store, String businessAccount) {
        User user = userService.findByUserName(businessAccount);
        if (user == null) {
            user = new User();
            Role role = roleService.findByRole(RoleEnum.BUSINESS.getValue());
            if (role == null) {
                return JsonResult.error("商家角色不存在");
            }
            user.setRoleId(role.getId());
            user.setUserName(businessAccount);
            user.setUserDisplayName(store.getBusinessName());
            user.setPhone(store.getBusinessPhone());
            user.setUserAvatar("/static/images/avatar/" + RandomUtils.nextInt(1, 41) + ".jpeg");
            userService.save(user);
        } else {
            Role role = roleService.findByUserId(user.getId());
            if (role == null || !Objects.equals(role.getRole(), RoleEnum.BUSINESS.getValue())) {
                return JsonResult.error("该账号不是商家账号");
            }
        }

        if(store.getId() == null) {
            store.setViewSize(0);
        }

        store.setBusinessUserId(user.getId());
        storeService.saveOrUpdate(store);
        return JsonResult.success("保存成功");
    }


    /**
     * 删除
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public JsonResult removeStore(@RequestParam("id") Long storeId) {
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Dish::getStoreId, storeId);
        int count = dishService.count(queryWrapper);
        if (count > 0) {
            return JsonResult.error("该店铺还有菜品，请先删除菜品");
        }

        storeService.removeById(storeId);
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
        for (Long id : ids) {
            LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Dish::getStoreId, id);
            int count = dishService.count(queryWrapper);
            if (count > 0) {
                return JsonResult.error("存在店铺还有菜品，请先删除对应菜品");
            }
        }
        return JsonResult.success("删除成功");
    }


    /**
     * 推荐菜品
     */
    @PostMapping(value = "/openStoreRecommend")
    @ResponseBody
    public JsonResult openStoreRecommend(Long id) {
        Store store = storeService.getById(id);
        if (store == null) {
            return JsonResult.error("菜品不存在");
        }
        store.setAdRecommend(1);
        storeService.updateById(store);
        return JsonResult.success("推荐成功");
    }

    /**
     * 关闭推荐
     */
    @PostMapping(value = "/closeStoreRecommend")
    @ResponseBody
    public JsonResult closeStoreRecommend(Long id) {
        Store store = storeService.getById(id);
        if (store == null) {
            return JsonResult.error("店铺不存在");
        }
        store.setAdRecommend(0);
        storeService.updateById(store);
        return JsonResult.success("取消推荐成功");
    }


}