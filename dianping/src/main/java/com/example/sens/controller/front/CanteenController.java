package com.example.sens.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sens.common.base.BaseController;
import com.example.sens.entity.Canteen;
import com.example.sens.entity.Store;
import com.example.sens.service.CanteenService;
import com.example.sens.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 食堂前台控制器
 */
@Controller("frontCanteenController")
@RequestMapping("/canteen")
public class CanteenController extends BaseController {

    @Autowired
    private CanteenService canteenService;

    @Autowired
    private StoreService storeService;

    /**
     * 食堂列表
     */
    @GetMapping
    public String canteens(Model model) {
        QueryWrapper<Canteen> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("view_size");
        List<Canteen> canteens = canteenService.list(queryWrapper);
        model.addAttribute("canteens", canteens);
        return "front/canteen/canteens";
    }

    /**
     * 食堂详情
     */
    @GetMapping("/{id}")
    public String canteen(@PathVariable("id") Long id, Model model) {
        // 获取食堂信息
        Canteen canteen = canteenService.getById(id);
        if (canteen == null) {
            return renderNotFound();
        }
        
        // 异步更新访问量
        CompletableFuture.runAsync(() -> {
            canteenService.incrementViewSize(id);
        });
        
        // 获取该食堂的店铺列表
        QueryWrapper<Store> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("canteen_id", id);
        List<Store> stores = storeService.list(queryWrapper);
        
        model.addAttribute("canteen", canteen);
        model.addAttribute("stores", stores);
        return "front/canteen/canteen";
    }
} 