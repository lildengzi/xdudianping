package com.example.sens.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sens.entity.Note;
import com.example.sens.entity.User;
import com.liuyanzhao.yztool.common.util.PageUtil;
import com.liuyanzhao.yztool.common.vo.JsonResult;
import com.example.sens.controller.common.BaseController;
import com.example.sens.entity.Mark;
import com.example.sens.service.*;
import com.example.sens.service.MarkService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 服务控制器
 *
 * @author saysky
 * @description
 * @since 2024-02-18 12:39:38
 */
@Slf4j
@Controller("adminMarkController")
@RequestMapping("/admin/mark")
public class MarkController extends BaseController {

    @Autowired
    private MarkService markService;

    @Autowired
    private UserService userService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private DishService dishService;

    /**
     * 分页列表
     */
    @GetMapping({"", "/list"})
    public String list(
            @RequestParam(value = "mainType", defaultValue = "-1") Integer mainType,
            @RequestParam(value = "keywords", defaultValue = "") String keywords,
            @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "order", defaultValue = "desc") String order,
            Model model) {
        Page page = PageUtil.initMpPage(pageNumber, pageSize, sort, order);
        LambdaQueryWrapper<Mark> queryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotEmpty(keywords)) {
            queryWrapper.like(Mark::getMainName, keywords);
        }
        if (mainType != null && mainType != -1) {
            queryWrapper.eq(Mark::getMainType, mainType);
        }

        if (!loginUserIsAdmin()) {
            queryWrapper.eq(Mark::getUserId, getLoginUserId());
        }

        IPage<Mark> marks = markService.page(page, queryWrapper);
        for (Mark mark : marks.getRecords()) {
            User user = userService.getById(mark.getUserId());
            mark.setUser(user == null ? new User() : user);
        }
        model.addAttribute("dataList", marks.getRecords());
        model.addAttribute("pageInfo", PageUtil.convertPageVo(page));
        model.addAttribute("keywords", keywords);
        model.addAttribute("mainType", mainType);

        model.addAttribute("isAdmin", loginUserIsAdmin());
        return "admin/mark/list";
    }

    /**
     * 添加页面
     */
    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("item", new Mark());


        return "admin/mark/edit";
    }


    /**
     * 添加页面
     */
    @GetMapping("/edit")
    public String editPage(@RequestParam Long id, Model model) {
        Mark mark = markService.getById(id);
        model.addAttribute("item", mark);
        return "admin/mark/edit";
    }


    /**
     * 详情页面
     */
    @GetMapping("/detail")
    public String detailPage(@RequestParam Long id, Model model) {
        Mark mark = markService.getById(id);
        model.addAttribute("item", mark);
        return "admin/mark/detail";
    }


    /**
     * 保存或添加提交
     */
    @PostMapping(value = "/save")
    @ResponseBody
    public JsonResult saveMark(Mark mark) {
        markService.saveOrUpdate(mark);
        return JsonResult.success("保存成功");
    }


    /**
     * 删除
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public JsonResult removeMark(@RequestParam("id") Long markId) {
        markService.removeById(markId);
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
        markService.removeByIds(ids);
        return JsonResult.success("删除成功");
    }
}