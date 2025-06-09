package com.example.sens.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sens.entity.Role;
import com.example.sens.entity.User;
import com.example.sens.service.*;
import com.example.sens.controller.common.BaseController;
import com.liuyanzhao.yztool.common.vo.JsonResult;
import com.liuyanzhao.yztool.common.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 后台用户管理控制器
 */
@Slf4j
@Controller
@RequestMapping(value = "/admin/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    public static final String USER_NAME = "userName";
    public static final String USER_DISPLAY_NAME = "userDisplayName";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";

    /**
     * 查询所有分类并渲染user页面
     *
     * @return 模板路径admin/admin_user
     */
    @GetMapping
    public String users(
            @RequestParam(value = "roleId", defaultValue = "-1") Long roleId,
            @RequestParam(value = "keywords", defaultValue = "") String keywords,
            @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sort", defaultValue = "createTime") String sort,
            @RequestParam(value = "order", defaultValue = "desc") String order, Model model) {
        //用户列表
        Page page = PageUtil.initMpPage(pageNumber, pageSize, sort, order);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(keywords)) {
            queryWrapper.like(User::getUserName, keywords)
                    .or().like(User::getUserDisplayName, keywords)
                    .or().like(User::getPhone, keywords)
                    .or().like(User::getEmail, keywords);
        }
        if (roleId != null && roleId != -1) {
            queryWrapper.eq(User::getRoleId, roleId);
        }

        IPage<User> users = userService.page(page, queryWrapper);
        for (User user : users.getRecords()) {
            Role role1 = roleService.findByUserId(user.getId());
            user.setRole(role1 != null ? role1 : new Role());
        }

        model.addAttribute("users", users.getRecords());
        model.addAttribute("pageInfo", PageUtil.convertPageVo(page));
        model.addAttribute("roleId", roleId);
        model.addAttribute("keywords", keywords);
        model.addAttribute("sort", sort);
        model.addAttribute("order", order);

        model.addAttribute("roleList", roleService.list());
        return "admin/user/admin_user";
    }


    /**
     * 删除用户
     *
     * @param userId 用户Id
     * @return JsonResult
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public JsonResult removeUser(@RequestParam("id") Long userId) {
        userService.removeById(userId);
        return JsonResult.success("删除成功");
    }

    /**
     * 添加用户页面
     *
     * @return 模板路径admin/admin_edit
     */
    @GetMapping("/new")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.list());
        return "admin/user/admin_user_edit";
    }

    /**
     * 编辑用户页面
     *
     * @return 模板路径admin/admin_edit
     */
    @GetMapping("/edit")
    public String edit(@RequestParam("id") Long userId, Model model) {
        User user = userService.getById(userId);
        if (user == null) {
            return this.renderNotFound();
        }
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.list());
        Role role = roleService.findByUserId(user.getId());
        model.addAttribute("currentRole", role);
        return "admin/user/admin_user_edit";
    }

    /**
     * 批量删除
     *
     * @param ids 用户ID列表
     * @return
     */
    @PostMapping(value = "/batchDelete")
    @ResponseBody
    public JsonResult batchDelete(@RequestParam("ids") List<Long> ids) {
        //批量操作
        if (ids == null || ids.size() == 0 || ids.size() >= 100) {
            return JsonResult.error("参数不合法!");
        }

        for (Long id : ids) {
            userService.removeById(id);
        }
        return JsonResult.success("删除成功");
    }

    /**
     * 新增/修改用户
     *
     * @param user user
     * @return 重定向到/admin/user
     */
    @PostMapping(value = "/save")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public JsonResult saveUser(@ModelAttribute User user) {
        if (user.getId() == null) {
            user.setCreateTime(new Date());
        }
        userService.saveOrUpdate(user);
        return JsonResult.success("保存成功");
    }

}
