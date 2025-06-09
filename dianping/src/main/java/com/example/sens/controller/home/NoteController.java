package com.example.sens.controller.home;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sens.controller.common.BaseController;
import com.example.sens.entity.*;
import com.example.sens.service.*;
import com.liuyanzhao.yztool.common.util.PageUtil;
import com.liuyanzhao.yztool.common.vo.JsonResult;
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
@Controller("noteController")
@RequestMapping("/note")
public class NoteController extends BaseController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserService userService;

    @Autowired
    private NoteCommentService noteCommentService;

    @Autowired
    private MarkService markService;

    @Autowired
    private NoteLikeService noteLikeService;

    /**
     * 分页列表
     */
    @GetMapping("")
    public String list(
            @RequestParam(value = "keywords", defaultValue = "") String keywords,
            @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "order", defaultValue = "desc") String order,
            Model model) {


        Page page = PageUtil.initMpPage(pageNumber, pageSize, sort, order);
        LambdaQueryWrapper<Note> queryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotEmpty(keywords)) {
            queryWrapper.like(Note::getTitle, keywords);
        }

        IPage<Note> notes = noteService.page(page, queryWrapper);
        for (Note note : notes.getRecords()) {
            User user = userService.getById(note.getUserId());
            note.setUser(user == null ? new User() : user);
        }

        model.addAttribute("dataList", notes.getRecords());
        model.addAttribute("pageInfo", PageUtil.convertPageVo(page));
        model.addAttribute("keywords", keywords);
        model.addAttribute("sort", sort);
        String title = "likeSize".equals(sort) ? "热门笔记" : "最新笔记";
        model.addAttribute("title", StringUtils.isNotEmpty(keywords) ? title + "，搜索" + keywords : title);
        return "home/note/list";
    }

    /**
     * 分页列表
     */
    @GetMapping("/user/{userId}")
    public String userNoteList(
            @RequestParam(value = "keywords", defaultValue = "") String keywords,
            @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "order", defaultValue = "desc") String order,
            @PathVariable Long userId,
            Model model) {
        User user = userService.getById(userId);
        if (user == null) {
            return renderNotFound();
        }

        Page page = PageUtil.initMpPage(pageNumber, pageSize, sort, order);
        LambdaQueryWrapper<Note> queryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotEmpty(keywords)) {
            queryWrapper.like(Note::getTitle, keywords);
        }
        queryWrapper.eq(Note::getUserId, userId);

        IPage<Note> notes = noteService.page(page, queryWrapper);
        for (Note note : notes.getRecords()) {
            note.setUser(user);
        }

        model.addAttribute("dataList", notes.getRecords());
        model.addAttribute("pageInfo", PageUtil.convertPageVo(page));
        model.addAttribute("keywords", keywords);
        model.addAttribute("user", user);
        model.addAttribute("likeSize", noteLikeService.countByUserId(userId));
        model.addAttribute("markSize", markService.countByUserId(userId, 3));
        model.addAttribute("commentSize", noteCommentService.countByUserId(userId));
        model.addAttribute("title", StringUtils.isNotEmpty(keywords) ? "搜索" + keywords : user.getUserDisplayName() + "的笔记列表");
        return "home/note/user_note_list";
    }

    /**
     * 笔记详情页面
     * 评论列表
     */
    @GetMapping("/{id}")
    public String detailPageWithComment(@PathVariable Long id,
                                        @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
                                        @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
                                        @RequestParam(value = "sort", defaultValue = "id") String sort,
                                        @RequestParam(value = "order", defaultValue = "desc") String order,
                                        Model model) {

        Note note = noteService.getById(id);
        if (note == null) {
            return renderNotFound();
        }
        User user1 = userService.getById(note.getUserId());
        note.setUser(user1 == null ? new User() : user1);
        model.addAttribute("item", note);

        // 菜品列表
        Page page = PageUtil.initMpPage(pageNumber, pageSize, sort, order);
        LambdaQueryWrapper<NoteComment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(NoteComment::getNoteId, id);
        queryWrapper.eq(NoteComment::getPid, 0);
        IPage<NoteComment> commentIPage = noteCommentService.page(page, queryWrapper);
        for (NoteComment comment : commentIPage.getRecords()) {
            // 评论作者
            User user = userService.getById(comment.getUserId());
            comment.setUser(user == null ? new User() : user);

            // 评论回复
            LambdaQueryWrapper<NoteComment> queryWrapper2 = new LambdaQueryWrapper<>();
            queryWrapper2.eq(NoteComment::getPid, comment.getId());
            List<NoteComment> childCommentList = noteCommentService.list(queryWrapper2);
            for (NoteComment reply : childCommentList) {
                // 评论作者
                User user2 = userService.getById(reply.getUserId());
                reply.setUser(user2 == null ? new User() : user2);
            }

            comment.setChildCommentList(childCommentList == null ? new ArrayList<>() : childCommentList);
        }
        model.addAttribute("dataList", commentIPage.getRecords());
        model.addAttribute("pageInfo", PageUtil.convertPageVo(page));

        boolean hasMark = false;
        boolean hasLike = false;
        if (getLoginUser() != null) {
            hasMark = markService.hasMark(getLoginUserId(), 3, id);
            hasLike = noteLikeService.isLike(id, getLoginUserId());
        }
        model.addAttribute("hasMark", hasMark);
        model.addAttribute("hasLike", hasLike);
        return "home/note/detail";
    }


    /**
     * 评论提交
     *
     * @param noteComment
     * @return
     */
    @PostMapping("/noteComment/save")
    @ResponseBody
    public JsonResult saveDishComment(NoteComment noteComment) {
        if (noteComment.getContent() == null || noteComment.getContent().length() > 200 || noteComment.getContent().length() < 2) {
            return JsonResult.error("评论内容字符为2-200个字符");
        }
        Note note = noteService.getById(noteComment.getNoteId());
        if (note == null) {
            return JsonResult.error("笔记不存在");
        }
        User loginUser = getLoginUser();
        if (loginUser == null) {
            return JsonResult.error("请先登录");
        }
        noteComment.setCreateTime(new Date());
        noteComment.setNoteId(note.getId());
        noteComment.setUserId(loginUser.getId());
        noteCommentService.save(noteComment);

        // 更新笔记评论数
        note.setCommentSize(noteCommentService.countByNoteId(note.getId()));
        noteService.updateById(note);
        return JsonResult.success("评论成功");
    }


    /**
     * 点赞或取消点赞
     *
     * @param noteId
     * @return
     */
    @PostMapping("/like")
    @ResponseBody
    public JsonResult like(Long noteId) {
        Note note = noteService.getById(noteId);
        if (note == null) {
            return JsonResult.error("笔记不存在");
        }
        User loginUser = getLoginUser();
        if (loginUser == null) {
            return JsonResult.error("用户未登录");
        }
        boolean isLike = noteLikeService.isLike(noteId, loginUser.getId());
        if (isLike) {
            noteLikeService.cancelLike(noteId, loginUser.getId());
            return JsonResult.success("取消点赞成功");
        }
        noteLikeService.like(noteId, loginUser.getId());
        return JsonResult.success("点赞成功");
    }


    /**
     * 收藏或取消收藏
     *
     * @param noteId
     * @return
     */
    @PostMapping("/mark")
    @ResponseBody
    public JsonResult mark(Long noteId) {
        Note note = noteService.getById(noteId);
        if (note == null) {
            return JsonResult.error("笔记不存在");
        }
        User loginUser = getLoginUser();
        if (loginUser == null) {
            return JsonResult.error("用户未登录");
        }
        boolean hasMark = markService.hasMark(loginUser.getId(), 3, noteId);
        if (hasMark) {
            markService.deleteMark(loginUser.getId(), 3, noteId);
            this.updateMarkSize(noteId);
            return JsonResult.success("取消收藏成功");
        }
        // 添加收藏
        markService.saveMark(loginUser.getId(), 3, note.getTitle(), noteId);
        this.updateMarkSize(noteId);
        return JsonResult.success("收藏成功");
    }


    /**
     * 更新笔记收藏数
     *
     * @param noteId
     */
    private void updateMarkSize(Long noteId) {
        Note note = noteService.getById(noteId);
        if (note == null) {
            return;
        }
        LambdaQueryWrapper<Mark> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Mark::getMainId, noteId);
        queryWrapper.eq(Mark::getMainType, 3);
        int count = markService.count(queryWrapper);
        note.setMarkSize(count);
        noteService.updateById(note);
    }


}