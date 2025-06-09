package com.example.sens.controller.home;

import cn.hutool.http.HtmlUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sens.common.constant.CommonConstant;
import com.example.sens.common.enums.CommentStatusEnum;
import com.liuyanzhao.yztool.common.util.PageUtil;
import com.liuyanzhao.yztool.common.vo.JsonResult;
import com.example.sens.controller.common.BaseController;
import com.example.sens.entity.Dish;
import com.example.sens.entity.DishComment;
import com.example.sens.entity.Store;
import com.example.sens.entity.User;
import com.example.sens.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
@Controller("dishController")
@RequestMapping("/dish")
public class DishController extends BaseController {

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
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotEmpty(keywords)) {
            queryWrapper.like(Dish::getName, keywords);
        }

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
        model.addAttribute("title", StringUtils.isNotEmpty(keywords) ? "搜索" + keywords : "菜品列表");
        return "home/dish/list";
    }

    /**
     * 详情页面
     */
    @GetMapping("/{id}")
    public String detailPage(@PathVariable Long id,
                             @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
                             @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
                             @RequestParam(value = "sort", defaultValue = "id") String sort,
                             @RequestParam(value = "order", defaultValue = "desc") String order,
                             HttpSession session,
                             Model model) {

        Dish dish = dishService.getById(id);
        if (dish == null) {
            return renderNotFound();
        }
        // 访问量+1
        dish.setViewSize(dish.getViewSize() + 1);
        dishService.updateById(dish);

        model.addAttribute("item", dish);

        // 评价列表
        LambdaQueryWrapper<DishComment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ne(DishComment::getStatus, CommentStatusEnum.NOT_CHECK);
        queryWrapper.eq(DishComment::getPid, 0);
        queryWrapper.eq(DishComment::getDishId, id);
        Page page = PageUtil.initMpPage(pageNumber, pageSize, sort, order);
        IPage<DishComment> dishCommentIPage = dishCommentService.page(page, queryWrapper);
        for (DishComment comment : dishCommentIPage.getRecords()) {
            // 评价作者
            User user = userService.getById(comment.getUserId());
            comment.setUser(user == null ? new User() : user);

            // 评价回复
            LambdaQueryWrapper<DishComment> queryWrapper2 = new LambdaQueryWrapper<>();
            queryWrapper2.eq(DishComment::getPid, comment.getId());
            queryWrapper2.ne(DishComment::getStatus, CommentStatusEnum.NOT_CHECK);
            List<DishComment> childCommentList = dishCommentService.list(queryWrapper2);
            for (DishComment reply : childCommentList) {
                // 评价作者
                User user2 = userService.getById(reply.getUserId());
                reply.setUser(user2 == null ? new User() : user2);
            }

            comment.setChildCommentList(childCommentList == null ? new ArrayList<>() : childCommentList);
        }

        model.addAttribute("dataList", dishCommentIPage.getRecords());
        model.addAttribute("pageInfo", PageUtil.convertPageVo(page));

        // 店铺
        Store store = storeService.getById(dish.getStoreId());
        model.addAttribute("store", store == null ? new Store() : store);

        model.addAttribute("hasMark", markService.hasMark(getLoginUserId(), 2, id));


        model.addAttribute("hasAddCart", hasAddCart(session, id));
        return "home/dish/detail";
    }



    /**
     * 是否在购物车中
     *
     * @param id
     * @return
     */
    public boolean hasAddCart(HttpSession session, Long id) {
        List<Long> dishIds = (List<Long>) session.getAttribute("cart");
        if (!CollectionUtils.isEmpty(dishIds) && dishIds.contains(id)) {
            return true;
        }
        return false;
    }

    /**
     * 写评论页面
     *
     * @param id
     * @return
     */
    @GetMapping("/writeComment/{id}")
    public String writeComment(@PathVariable("id") Long id, Model model) {
        Dish dish = dishService.getById(id);
        if (dish == null) {
            return renderNotFound();
        }
        model.addAttribute("item", dish);

        return "home/dish/writeComment";
    }

    /**
     * 评价提交
     *
     * @param dishComment
     * @return
     */
    @PostMapping("/dishComment/save")
    @ResponseBody
    public JsonResult saveDishComment(DishComment dishComment) {
        if (dishComment.getScore() == null || dishComment.getScore() > 5 || dishComment.getScore() < 1) {
            return JsonResult.error("评分不合法");
        }

        Dish dish = dishService.getById(dishComment.getDishId());
        if (dish == null) {
            return JsonResult.error("菜品不存在");
        }
        if (loginUserIsBusiness()) {
            return JsonResult.error("商家不能进行评价");
        }
        User loginUser = getLoginUser();
        if (loginUser == null) {
            return JsonResult.error("请先登录");
        }

        dishComment.setCreateTime(new Date());
        dishComment.setStatus(CommentStatusEnum.NOT_CHECK.getValue());
        dishComment.setStoreId(dish.getStoreId());
        dishComment.setUserId(loginUser.getId());
        dishComment.setPid(0L);
        dishCommentService.save(dishComment);
        return JsonResult.success("评价成功，请耐心等待管理员审核");
    }

}