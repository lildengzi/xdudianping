package com.example.sens.controller.admin;

import cn.hutool.http.HtmlUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sens.common.constant.CommonConstant;
import com.example.sens.common.enums.CommentStatusEnum;
import com.example.sens.entity.StoreComment;
import com.example.sens.entity.User;
import com.liuyanzhao.yztool.common.util.PageUtil;
import com.liuyanzhao.yztool.common.vo.JsonResult;
import com.example.sens.controller.common.BaseController;
import com.example.sens.entity.Dish;
import com.example.sens.entity.DishComment;
import com.example.sens.entity.Store;
import com.example.sens.service.DishCommentService;
import com.example.sens.service.DishService;
import com.example.sens.service.StoreService;
import com.example.sens.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
@Controller("adminDishCommentController")
@RequestMapping("/admin/dishComment")
public class DishCommentController extends BaseController {

    @Autowired
    private DishCommentService dishCommentService;

    @Autowired
    private UserService userService;

    @Autowired
    private DishService dishService;

    @Autowired
    private StoreService storeService;

    /**
     * 分页列表
     */
    @GetMapping({"", "/list"})
    public String list(
            @RequestParam(value = "storeId", defaultValue = "-1") Long storeId,
            @RequestParam(value = "keywords", defaultValue = "") String keywords,
            @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "order", defaultValue = "desc") String order,
            Model model) {
        Page page = PageUtil.initMpPage(pageNumber, pageSize, sort, order);
        LambdaQueryWrapper<DishComment> queryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotEmpty(keywords)) {
            queryWrapper.eq(DishComment::getContent, keywords);
        }
        if (storeId != -1) {
            queryWrapper.eq(DishComment::getStoreId, storeId);
        }
        if (loginUserIsBusiness()) {
            // 商家只能看到已经审核的评价
            queryWrapper.ne(DishComment::getStatus, CommentStatusEnum.NOT_CHECK.getValue());
            // 只能看到自己店铺的评价
            LambdaQueryWrapper<Store> queryWrapper2 = new LambdaQueryWrapper<>();
            if (!loginUserIsAdmin()) {
                queryWrapper2.eq(Store::getBusinessUserId, getLoginUserId());
            }
            List<Store> storeList = storeService.list(queryWrapper2);
            if (storeList.size() > 0) {
                List<Long> storeIds = storeList.stream().map(p -> p.getId()).collect(Collectors.toList());
                queryWrapper.in(DishComment::getStoreId, storeIds);
            } else {
                queryWrapper.in(DishComment::getStoreId, -1);
            }
        }

        if (loginUserIsCustomer()) {
            // 客户只能看到自己的评价
            queryWrapper.eq(DishComment::getUserId, getLoginUserId());
        }


        // 只查询一级评价
        queryWrapper.eq(DishComment::getPid, 0);

        IPage<DishComment> dishComments = dishCommentService.page(page, queryWrapper);


        for (DishComment item : dishComments.getRecords()) {
            String summaryText = HtmlUtil.cleanHtmlTag(item.getContent());
            if (summaryText.length() > CommonConstant.SUMMARY_LENGTH) {
                summaryText = summaryText.substring(0, CommonConstant.SUMMARY_LENGTH) + "...";
            }
            item.setContent(summaryText);

            Store store = storeService.getById(item.getStoreId());
            Dish dish = dishService.getById(item.getDishId());
            User user = userService.getById(item.getUserId());
            item.setStore(store == null ? new Store() : store);
            item.setDish(dish == null ? new Dish() : dish);
            item.setUser(user == null ? new User() : user);
        }
        model.addAttribute("dataList", dishComments.getRecords());
        model.addAttribute("pageInfo", PageUtil.convertPageVo(page));
        model.addAttribute("keywords", keywords);
        model.addAttribute("storeId", storeId);

        model.addAttribute("isAdmin", loginUserIsAdmin());
        model.addAttribute("isBusiness", loginUserIsBusiness());
        model.addAttribute("isCustomer", loginUserIsCustomer());

        LambdaQueryWrapper<Store> queryWrapper2 = new LambdaQueryWrapper<>();
        if (!loginUserIsAdmin()) {
            queryWrapper2.eq(Store::getBusinessUserId, getLoginUserId());
        }
        model.addAttribute("storeList", storeService.list(queryWrapper2));
        return "admin/dishComment/list";
    }

    /**
     * 添加页面
     */
    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("item", new DishComment());


        return "admin/dishComment/edit";
    }


    /**
     * 添加页面
     */
    @GetMapping("/edit")
    public String editPage(@RequestParam Long id, Model model) {
        DishComment dishComment = dishCommentService.getById(id);
        model.addAttribute("item", dishComment);
        return "admin/dishComment/edit";
    }


    /**
     * 详情页面
     */
    @GetMapping("/detail")
    public String detailPage(@RequestParam Long id, Model model) {
        DishComment dishComment = dishCommentService.getById(id);
        model.addAttribute("item", dishComment);

        // 查询回复评论
        LambdaQueryWrapper<DishComment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(DishComment::getId);
        queryWrapper.eq(DishComment::getPid, id);
        List<DishComment> replyList = dishCommentService.list(queryWrapper);
        model.addAttribute("replyList", replyList);

        model.addAttribute("isAdmin", loginUserIsAdmin());
        model.addAttribute("isBusiness", loginUserIsBusiness());
        model.addAttribute("isCustomer", loginUserIsCustomer());
        return "admin/dishComment/detail";
    }


    /**
     * 保存或添加提交
     */
    @PostMapping(value = "/save")
    @ResponseBody
    public JsonResult saveDishComment(DishComment dishComment) {
        dishCommentService.saveOrUpdate(dishComment);
        return JsonResult.success("保存成功");
    }

    /**
     * 保存或添加提交
     */
    @PostMapping(value = "/reply")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public JsonResult replyDishComment(Long id,
                                       String content) {
        DishComment dishComment = dishCommentService.getById(id);
        if (dishComment == null) {
            return JsonResult.error("评价不存在");
        }
        if (Objects.equals(dishComment.getStatus(), CommentStatusEnum.NOT_CHECK.getValue())) {
            return JsonResult.error("评价未审核");
        }

        // 更新评价状态为已回复
        dishComment.setStatus(CommentStatusEnum.HAS_REPLY.getValue());
        dishCommentService.updateById(dishComment);

        // 添加回复内容
        DishComment replyDishComment = new DishComment();
        replyDishComment.setCreateTime(new Date());
        replyDishComment.setUserId(getLoginUserId());
        replyDishComment.setStatus(CommentStatusEnum.HAS_CHECK.getValue());
        replyDishComment.setContent(content);
        replyDishComment.setPid(id);
        replyDishComment.setStoreId(dishComment.getStoreId());
        replyDishComment.setDishId(dishComment.getDishId());
        dishCommentService.save(replyDishComment);
        return JsonResult.success("回复成功");
    }


    /**
     * 删除
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public JsonResult removeDishComment(@RequestParam("id") Long dishCommentId) {
        dishCommentService.removeById(dishCommentId);
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
        dishCommentService.removeByIds(ids);
        return JsonResult.success("删除成功");
    }

    /**
     * 审核通过
     */
    @PostMapping(value = "/pass")
    @ResponseBody
    public JsonResult pass(@RequestParam("id") Long dishCommentId) {
        DishComment dishComment = dishCommentService.getById(dishCommentId);
        dishComment.setStatus(CommentStatusEnum.HAS_CHECK.getValue());
        dishCommentService.updateById(dishComment);
        return JsonResult.success("审核通过成功");
    }


    /**
     * 审核打回
     */
    @PostMapping(value = "/reject")
    @ResponseBody
    public JsonResult rejectIt(@RequestParam("id") Long dishCommentId) {
        DishComment dishComment = dishCommentService.getById(dishCommentId);
        dishComment.setStatus(CommentStatusEnum.HAS_REJECT.getValue());
        dishCommentService.updateById(dishComment);
        return JsonResult.success("审核驳回成功");
    }
}