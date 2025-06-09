package com.example.sens.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sens.common.base.BaseController;
import com.example.sens.entity.Canteen;
import com.example.sens.service.CanteenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 食堂管理后台控制器
 */
@Controller("adminCanteenController")
@RequestMapping("/admin/canteen")
public class CanteenController extends BaseController {

    @Autowired
    private CanteenService canteenService;

    /**
     * 食堂列表
     */
    @GetMapping
    public String canteens(@RequestParam(value = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "size", defaultValue = "10") Integer size,
                          @RequestParam(value = "keywords", required = false) String keywords,
                          Model model) {
        Page<Canteen> pageInfo = new Page<>(page, size);
        QueryWrapper<Canteen> queryWrapper = new QueryWrapper<>();
        if (keywords != null) {
            queryWrapper.like("name", keywords).or().like("description", keywords);
        }
        IPage<Canteen> canteenPage = canteenService.page(pageInfo, queryWrapper);
        model.addAttribute("pageInfo", canteenPage);
        model.addAttribute("keywords", keywords);
        return "admin/canteen/canteens";
    }

    /**
     * 新增食堂页面
     */
    @GetMapping("/new")
    public String newCanteen() {
        return "admin/canteen/new";
    }

    /**
     * 编辑食堂页面
     */
    @GetMapping("/edit/{id}")
    public String editCanteen(@PathVariable("id") Long id, Model model) {
        Canteen canteen = canteenService.getById(id);
        model.addAttribute("canteen", canteen);
        return "admin/canteen/edit";
    }

    /**
     * 保存食堂
     */
    @PostMapping("/save")
    @ResponseBody
    public boolean saveCanteen(@RequestBody Canteen canteen) {
        if (canteen.getId() == null) {
            canteen.setCreateTime(new Date());
            canteen.setViewSize(0);
        }
        return canteenService.saveOrUpdate(canteen);
    }

    /**
     * 删除食堂
     */
    @PostMapping("/delete")
    @ResponseBody
    public boolean deleteCanteen(@RequestParam("id") Long id) {
        return canteenService.removeById(id);
    }

    /**
     * 修改食堂状态
     */
    @PostMapping("/status")
    @ResponseBody
    public boolean updateStatus(@RequestParam("id") Long id, @RequestParam("status") Integer status) {
        Canteen canteen = new Canteen();
        canteen.setId(id);
        canteen.setStatus(status);
        return canteenService.updateById(canteen);
    }
} 