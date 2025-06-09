package com.example.sens.controller.home;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sens.common.enums.CommentStatusEnum;
import com.liuyanzhao.yztool.common.util.PageUtil;
import com.liuyanzhao.yztool.common.vo.JsonResult;
import com.example.sens.controller.common.BaseController;
import com.example.sens.entity.*;
import com.example.sens.entity.Store;
import com.example.sens.service.*;
import com.example.sens.service.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 服务控制器
 *
 * @author saysky
 * @description
 * @since 2024-02-18 12:39:38
 */
@Slf4j
@Controller("storeController")
@RequestMapping("/store")
public class StoreController extends BaseController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private UserService userService;

    @Autowired
    private StoreCommentService storeCommentService;

    @Autowired
    private DishService dishService;

    @Autowired
    private DishNoticeService dishNoticeService;

    @Autowired
    private MarkService markService;

    /**
     * 分页列表
     */
    @GetMapping({"", "/list"})
    public String list(
            @RequestParam(value = "keywords", defaultValue = "") String keywords,
            @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "order", defaultValue = "desc") String order,
            Model model) {


        Page page = PageUtil.initMpPage(pageNumber, pageSize, sort, order);
        LambdaQueryWrapper<Store> queryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotEmpty(keywords)) {
            queryWrapper.like(Store::getName, keywords);
        }

        IPage<Store> stores = storeService.page(page, queryWrapper);

        model.addAttribute("dataList", stores.getRecords());
        model.addAttribute("pageInfo", PageUtil.convertPageVo(page));
        model.addAttribute("keywords", keywords);
        model.addAttribute("title", StringUtils.isNotEmpty(keywords) ? "搜索" + keywords : "店铺列表");
        return "home/store/list";
    }

    /**
     * 店铺详情页面
     * 菜品列表
     */
    @GetMapping("/{id}")
    public String detailPage(@PathVariable Long id,
                             @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
                             @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
                             @RequestParam(value = "sort", defaultValue = "id") String sort,
                             @RequestParam(value = "order", defaultValue = "desc") String order,
                             Model model) {

        Store store = storeService.getById(id);
        if (store == null) {
            return renderNotFound();
        }
        // 访问量+1
        store.setViewSize(store.getViewSize() + 1);
        storeService.updateById(store);
        model.addAttribute("item", store);

        // 菜品列表
        Page page = PageUtil.initMpPage(pageNumber, pageSize, sort, order);
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Dish::getStoreId, id);
        IPage<Dish> dishIPage = dishService.page(page, queryWrapper);
        model.addAttribute("dataList", dishIPage.getRecords());
        model.addAttribute("pageInfo", PageUtil.convertPageVo(page));

        model.addAttribute("hasMark", markService.hasMark(getLoginUserId(), 1, id));
        return "home/store/detail";
    }

    /**
     * 店铺详情页面
     * 菜品预告列表
     */
    @GetMapping("/{id}/notice")
    public String detailPageWithNotice(@PathVariable Long id,
                                       @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
                                       @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
                                       @RequestParam(value = "sort", defaultValue = "id") String sort,
                                       @RequestParam(value = "order", defaultValue = "desc") String order,
                                       Model model) {

        Store store = storeService.getById(id);
        if (store == null) {
            return renderNotFound();
        }
        model.addAttribute("item", store);

        // 菜品列表
        Page page = PageUtil.initMpPage(pageNumber, pageSize, sort, order);
        LambdaQueryWrapper<DishNotice> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishNotice::getStoreId, id);
        IPage<DishNotice> dishNoticeIPage = dishNoticeService.page(page, queryWrapper);
        model.addAttribute("dataList", dishNoticeIPage.getRecords());
        model.addAttribute("pageInfo", PageUtil.convertPageVo(page));

        model.addAttribute("hasMark", markService.hasMark(getLoginUserId(), 1, id));
        return "home/store/detail_notice";
    }


    /**
     * 店铺详情页面
     * 评价列表
     */
    @GetMapping("/{id}/comment")
    public String detailPageWithComment(@PathVariable Long id,
                                        @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
                                        @RequestParam(value = "size", defaultValue = "1") Integer pageSize,
                                        @RequestParam(value = "sort", defaultValue = "id") String sort,
                                        @RequestParam(value = "order", defaultValue = "desc") String order,
                                        Model model) {

        Store store = storeService.getById(id);
        if (store == null) {
            return renderNotFound();
        }
        model.addAttribute("item", store);

        // 菜品列表
        Page page = PageUtil.initMpPage(pageNumber, pageSize, sort, order);
        LambdaQueryWrapper<StoreComment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StoreComment::getStoreId, id);
        queryWrapper.eq(StoreComment::getPid, 0);
        IPage<StoreComment> commentIPage = storeCommentService.page(page, queryWrapper);
        for (StoreComment comment : commentIPage.getRecords()) {
            // 评价作者
            User user = userService.getById(comment.getUserId());
            comment.setUser(user == null ? new User() : user);

            // 评价回复
            LambdaQueryWrapper<StoreComment> queryWrapper2 = new LambdaQueryWrapper<>();
            queryWrapper2.eq(StoreComment::getPid, comment.getId());
            queryWrapper2.ne(StoreComment::getStatus, CommentStatusEnum.NOT_CHECK);
            List<StoreComment> childCommentList = storeCommentService.list(queryWrapper2);
            for (StoreComment reply : childCommentList) {
                // 评价作者
                User user2 = userService.getById(reply.getUserId());
                reply.setUser(user2 == null ? new User() : user2);
            }

            comment.setChildCommentList(childCommentList == null ? new ArrayList<>() : childCommentList);
        }
        model.addAttribute("dataList", commentIPage.getRecords());
        model.addAttribute("pageInfo", PageUtil.convertPageVo(page));

        model.addAttribute("hasMark", markService.hasMark(getLoginUserId(), 1, id));
        return "home/store/detail_comment";
    }


    /**
     * 评价提交
     *
     * @param storeComment
     * @return
     */
    @PostMapping("/storeComment/save")
    @ResponseBody
    public JsonResult saveDishComment(StoreComment storeComment) {
        if (storeComment.getScore() == null || storeComment.getScore() > 5 || storeComment.getScore() < 1) {
            return JsonResult.error("总体评分不合法");
        }
        if (storeComment.getEnvironmentScore() == null || storeComment.getEnvironmentScore() > 5 || storeComment.getEnvironmentScore() < 1) {
            return JsonResult.error("环境评分不合法");
        }
        if (storeComment.getServiceScore() == null || storeComment.getServiceScore() > 5 || storeComment.getServiceScore() < 1) {
            return JsonResult.error("服务评分不合法");
        }
        if (storeComment.getTasteScore() == null || storeComment.getTasteScore() > 5 || storeComment.getTasteScore() < 1) {
            return JsonResult.error("口味评分不合法");
        }
        if (storeComment.getContent() == null || storeComment.getContent().length() > 200 || storeComment.getContent().length() < 2) {
            return JsonResult.error("评价内容字符为2-200个字符");
        }
        Store store = storeService.getById(storeComment.getStoreId());
        if (store == null) {
            return JsonResult.error("店铺不存在");
        }
        User loginUser = getLoginUser();
        if (loginUser == null) {
            return JsonResult.error("请先登录");
        }
        if (loginUserIsBusiness()) {
            return JsonResult.error("商家不能进行评价");
        }
        storeComment.setCreateTime(new Date());
        storeComment.setStatus(CommentStatusEnum.NOT_CHECK.getValue());
        storeComment.setStoreId(store.getId());
        storeComment.setUserId(loginUser.getId());
        storeComment.setPid(0L);
        storeCommentService.save(storeComment);
        return JsonResult.success("评价成功，请耐心等待管理员审核");
    }

}