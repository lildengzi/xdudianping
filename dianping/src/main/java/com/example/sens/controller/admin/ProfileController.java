package com.example.sens.controller.admin;

import com.example.sens.controller.common.BaseController;
import com.example.sens.entity.Role;
import com.example.sens.entity.User;
import com.liuyanzhao.yztool.common.vo.JsonResult;
import com.example.sens.service.RoleService;
import com.example.sens.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * 后台用户管理控制器
 */
@Slf4j
@Controller
@RequestMapping(value = "/admin/user")
public class ProfileController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 获取用户信息并跳转
     *
     * @return 模板路径admin/admin_profile
     */
    @GetMapping("/profile")
    public String profile(Model model) {
        User user = userService.getById(getLoginUserId());
        model.addAttribute("user", user);

        model.addAttribute("user", user);
        return "admin/admin_profile";
    }

    /**
     * 处理修改用户资料的请求
     *
     * @param user user
     * @return JsonResult
     */
    @PostMapping(value = "/profile/save")
    @ResponseBody
    public JsonResult saveProfile(@ModelAttribute User user, HttpSession session) {
        User loginUser = getLoginUser();

        user.setId(loginUser.getId());
        user.setRoleId(loginUser.getRoleId());
        userService.saveOrUpdate(user);

        Role role = roleService.findByUserId(loginUser.getId());
        user.setRole(role);
        session.setAttribute("user", user);
        return JsonResult.success("资料修改成功");
    }


    /**
     * 处理修改密码的请求
     *
     * @param beforePass 旧密码
     * @param newPass    新密码
     * @return JsonResult
     */
    @RequestMapping(method = RequestMethod.POST, value = "/changePass")
    @ResponseBody
    public JsonResult changePass(@RequestParam(value = "id", required = false) Long id,
                                 @RequestParam(value = "beforePass", required = false) String beforePass,
                                 @RequestParam("newPass") String newPass) {
        User loginUser = getLoginUser();
        if (id == null) {
            // 用户修改密码
            User user = userService.getById(loginUser.getId());
            if (user != null && Objects.equals(user.getUserPass(), beforePass)) {
                userService.updatePassword(user.getId(), newPass);
            } else {
                return JsonResult.error("旧密码错误");
            }
        } else {
            // 管理员修改用户密码
            if (!loginUserIsAdmin()) {
                return JsonResult.error("无权操作");
            }
            userService.updatePassword(id, newPass);
        }

        return JsonResult.success("密码重置成功");
    }


}
