package com.example.sens.controller.home;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sens.common.enums.CommentStatusEnum;
import com.example.sens.entity.DishNotice;
import com.example.sens.service.DishNoticeService;
import com.example.sens.service.MarkService;
import com.liuyanzhao.yztool.common.util.PageUtil;
import com.example.sens.controller.common.BaseController;
import com.example.sens.entity.Dish;
import com.example.sens.entity.DishComment;
import com.example.sens.entity.Store;
import com.example.sens.entity.User;
import com.example.sens.service.DishCommentService;
import com.example.sens.service.DishService;
import com.example.sens.service.StoreService;
import com.example.sens.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务控制器
 *
 * @author saysky
 * @description
 * @since 2024-02-18 12:39:38
 */
@Slf4j
@Controller("dishNoticeController")
@RequestMapping("/dishNotice")
public class DishNoticeController extends BaseController {

    @Autowired
    private DishService dishService;

    @Autowired
    private UserService userService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private DishCommentService dishCommentService;

    @Autowired
    private MarkService markService;

    @Autowired
    private DishNoticeService dishNoticeService;
    /**
     * 菜品预告详情页面
     */
    @GetMapping("/{id}")
    public String detailPage(@PathVariable Long id,
                             @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
                             @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
                             @RequestParam(value = "sort", defaultValue = "id") String sort,
                             @RequestParam(value = "order", defaultValue = "desc") String order,
                             Model model) {

        DishNotice dishNotice = dishNoticeService.getById(id);
        if (dishNotice == null) {
            return renderNotFound();
        }
        model.addAttribute("item", dishNotice);


        // 店铺
        Store store = storeService.getById(dishNotice.getStoreId());
        model.addAttribute("store", store == null ? new Store() : store);

        return "home/dishNotice/detail";
    }


}