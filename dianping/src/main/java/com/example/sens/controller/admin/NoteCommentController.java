package com.example.sens.controller.admin;

import cn.hutool.http.HtmlUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sens.common.constant.CommonConstant;
import com.example.sens.common.enums.CommentStatusEnum;
import com.example.sens.controller.common.BaseController;
import com.example.sens.entity.Note;
import com.example.sens.entity.NoteComment;
import com.example.sens.entity.User;
import com.example.sens.service.NoteCommentService;
import com.example.sens.service.NoteService;
import com.example.sens.service.UserService;
import com.liuyanzhao.yztool.common.util.PageUtil;
import com.liuyanzhao.yztool.common.vo.JsonResult;
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
@Controller("adminNoteCommentController")
@RequestMapping("/admin/noteComment")
public class NoteCommentController extends BaseController {

    @Autowired
    private NoteCommentService noteCommentService;

    @Autowired
    private UserService userService;

    @Autowired
    private NoteService noteService;

    /**
     * 我发出的评论
     */
    @GetMapping({"", "/list"})
    public String list(
            @RequestParam(value = "keywords", defaultValue = "") String keywords,
            @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "order", defaultValue = "desc") String order,
            Model model) {
        // 分页查询
        Page<NoteComment> page = PageUtil.initMpPage(pageNumber, pageSize, sort, order);

        Long userId = -1L;
        if (!loginUserIsAdmin()) {
            userId = getLoginUserId();
        }
        IPage<NoteComment> noteComments = noteCommentService.getNoteCommentPage(userId, keywords, page);
        for (NoteComment item : noteComments.getRecords()) {
            String summaryText = HtmlUtil.cleanHtmlTag(item.getContent());
            if (summaryText.length() > CommonConstant.SUMMARY_LENGTH) {
                summaryText = summaryText.substring(0, CommonConstant.SUMMARY_LENGTH) + "...";
            }
            item.setContent(summaryText);
            Note note = noteService.getById(item.getNoteId());
            User user = userService.getById(item.getUserId());
            item.setNote(note == null ? new Note() : note);
            item.setUser(user == null ? new User() : user);
        }
        model.addAttribute("dataList", noteComments.getRecords());
        model.addAttribute("pageInfo", PageUtil.convertPageVo(page));
        model.addAttribute("keywords", keywords);

        model.addAttribute("isAdmin", loginUserIsAdmin());
        return "admin/noteComment/list";
    }


    /**
     * 详情页面
     */
    @GetMapping("/detail")
    public String detailPage(@RequestParam Long id, Model model) {
        NoteComment noteComment = noteCommentService.getById(id);
        model.addAttribute("item", noteComment);

        // 查询回复评论
        LambdaQueryWrapper<NoteComment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(NoteComment::getId);
        queryWrapper.eq(NoteComment::getPid, id);
        List<NoteComment> replyList = noteCommentService.list(queryWrapper);
        model.addAttribute("replyList", replyList);

        model.addAttribute("isAdmin", loginUserIsAdmin());
        model.addAttribute("isBusiness", loginUserIsBusiness());
        model.addAttribute("isCustomer", loginUserIsCustomer());

        return "admin/noteComment/detail";
    }


    /**
     * 删除
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public JsonResult removeStoreComment(@RequestParam("id") Long storeCommentId) {
        noteCommentService.removeById(storeCommentId);
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
        noteCommentService.removeByIds(ids);
        return JsonResult.success("删除成功");
    }
}