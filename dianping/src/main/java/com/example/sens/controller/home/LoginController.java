package com.example.sens.controller.home;

import cn.hutool.core.util.RandomUtil;
import com.example.sens.controller.common.BaseController;
import com.example.sens.entity.Role;
import com.example.sens.entity.User;
import com.liuyanzhao.yztool.common.vo.JsonResult;
import com.example.sens.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Objects;

@Controller
@Slf4j
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 验证登录信息
     *
     * @param userName 用户名
     * @param userPass password 密码
     * @return JsonResult JsonResult
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public JsonResult getLogin(@RequestParam("userName") String userName,
                               @RequestParam("userPass") String userPass,
                               HttpSession session) {
        User user = userService.findByUserName(userName);
        if (user == null) {
            return JsonResult.error("用户名不存在");
        }
        if (!Objects.equals(user.getUserPass(), userPass)) {
            return JsonResult.error("密码错误");
        }
        // 登录成功
        Role role = roleService.findByUserId(user.getId());
        user.setRole(role);
        session.setAttribute("user", user);
        return JsonResult.success("登录成功");
    }


    /**
     * 退出登录
     *
     * @return 重定向到/login
     */
    @GetMapping(value = "/logout")
    public String logOut(HttpSession session) {
        session.removeAttribute("user");
//        session.invalidate();
        return "redirect:/login";
    }

    /**
     * 验证注册信息
     *
     * @return JsonResult JsonResult
     */
    @PostMapping(value = "/register")
    @ResponseBody
    @Transactional
    public JsonResult getRegister(@RequestParam("userName") String userName,
                                  @RequestParam("realName") String realName,
                                  @RequestParam("phone") String phone,
                                  @RequestParam("email") String email,
                                  @RequestParam("userPass") String userPass
    ) {
        // 1.校验是否输入完整
        if (StringUtils.isEmpty(userName) ||
                StringUtils.isEmpty(realName) ||
                StringUtils.isEmpty(phone) ||
                StringUtils.isEmpty(email) ||
                StringUtils.isEmpty(userPass)) {
            return JsonResult.error("请填写完整信息");
        }

        // 2.密码长度是否合法
        if (userPass.length() > 20 || userPass.length() < 4) {
            return JsonResult.error("用户密码长度为4-20位!");
        }


        Role defaultRole = roleService.findDefaultRole();
        if (defaultRole == null) {
            return JsonResult.error("注册失败，系统未设置默认注册角色");
        }
        //3.创建用户
        User user = new User();
        user.setUserName(userName);
        user.setUserDisplayName(realName);
        user.setPhone(phone);
        user.setUserPass(userPass);
        user.setEmail(email);
        user.setUserAvatar("/static/images/avatar/" + RandomUtils.nextInt(1, 41) + ".jpeg");
        user.setCreateTime(new Date());
        user.setRoleId(defaultRole.getId());
        userService.save(user);
        return JsonResult.success("注册成功");
    }

    /**
     * 验证找回密码
     *
     * @param userName  用户名
     * @param userEmail 邮箱
     * @return JsonResult JsonResult
     */
    @PostMapping(value = "/forget")
    @ResponseBody
    public JsonResult getForget(@RequestParam("userName") String userName,
                                @RequestParam("email") String userEmail,
                                HttpSession session) {
        User user = userService.findByUserName(userName);
        if (user == null) {
            return JsonResult.error("用户名不存在");
        }
        if (!Objects.equals(user.getEmail(), userEmail)) {
            return JsonResult.error("用户名和邮箱不匹配");
        }
        // 验证通过，重置密码
        String newPassword = RandomUtil.randomString(6);
        userService.updatePassword(user.getId(), newPassword);
        return JsonResult.success("重置成功，新密码为" + newPassword);
    }


    @GetMapping("/login")
    public String login() {
        return "home/login";
    }

    @GetMapping("/register")
    public String register() {
        return "home/register";
    }

    @GetMapping("/forget")
    public String forget() {
        return "home/forget";
    }


    /**
     * 检查用户是否登录
     *
     * @return JsonResult
     */
    @GetMapping(value = "/checkLogin")
    @ResponseBody
    public JsonResult checkLogin() {
        User user = getLoginUser();
        if (user == null) {
            return JsonResult.error("请先登录");
        } else {
            return JsonResult.success();
        }
    }


}
