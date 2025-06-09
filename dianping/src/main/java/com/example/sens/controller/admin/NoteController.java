package com.example.sens.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sens.entity.Store;
import com.example.sens.entity.User;
import com.liuyanzhao.yztool.common.util.PageUtil;
import com.liuyanzhao.yztool.common.vo.JsonResult;
import com.example.sens.controller.common.BaseController;
import com.example.sens.entity.Note;
import com.example.sens.service.NoteService;
import com.example.sens.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
@Controller("adminNoteController")
@RequestMapping("/admin/note")
public class NoteController extends BaseController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserService userService;

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
        LambdaQueryWrapper<Note> queryWrapper = new LambdaQueryWrapper();
        if (!loginUserIsAdmin()) {
            queryWrapper.eq(Note::getUserId, getLoginUserId());
        }
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

        model.addAttribute("isAdmin", loginUserIsAdmin());
        return "admin/note/list";
    }

    /**
     * 添加页面
     */
    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("item", new Note());


        return "admin/note/edit";
    }


    /**
     * 添加页面
     */
    @GetMapping("/edit")
    public String editPage(@RequestParam Long id, Model model) {
        Note note = noteService.getById(id);
        model.addAttribute("item", note);
        return "admin/note/edit";
    }


    /**
     * 详情页面
     */
    @GetMapping("/detail")
    public String detailPage(@RequestParam Long id, Model model) {
        Note note = noteService.getById(id);
        model.addAttribute("item", note);
        return "admin/note/detail";
    }


    /**
     * 保存或添加提交
     */
    @PostMapping(value = "/save")
    @ResponseBody
    public JsonResult saveNote(Note note) {
        if (note.getId() == null) {
            note.setUserId(getLoginUserId());
            note.setLikeSize(0);
            note.setMarkSize(0);
            note.setCommentSize(0);
            note.setCreateTime(new Date());
        }
        noteService.saveOrUpdate(note);
        return JsonResult.success("保存成功");
    }


    /**
     * 删除
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public JsonResult removeNote(@RequestParam("id") Long noteId) {
        noteService.removeById(noteId);
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
        noteService.removeByIds(ids);
        return JsonResult.success("删除成功");
    }
}