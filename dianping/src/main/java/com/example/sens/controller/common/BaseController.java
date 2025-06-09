package com.example.sens.controller.common;

import com.example.sens.entity.Dish;
import com.example.sens.entity.User;
import com.example.sens.common.enums.RoleEnum;
import com.example.sens.service.DishService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller抽象类
 */
public abstract class BaseController {

    @Autowired
    private HttpServletRequest request;

    /**
     * 渲染404页面
     *
     * @return redirect:/404
     */
    public String renderNotFound() {
        return "forward:/404";
    }


    /**
     * 渲染404页面
     *
     * @return redirect:/404
     */
    public String renderNotAllowAccess() {
        return "forward:/403";
    }

    /**
     * 当前登录用户
     *
     * @return
     */
    public User getLoginUser() {
        User user = (User) request.getSession().getAttribute("user");
        return user;
    }

    /**
     * 当前用户ID
     *
     * @return
     */
    public Long getLoginUserId() {
        User user = getLoginUser();
        if (user == null) {
            return null;
        }
        return user.getId();
    }

    /**
     * 当前用户是管理员
     *
     * @return
     */
    public Boolean loginUserIsAdmin() {
        User loginUser = getLoginUser();
        if (loginUser != null) {
            return RoleEnum.ADMIN.getValue().equalsIgnoreCase(loginUser.getRole().getRole());
        }

        return false;
    }

    /**
     * 当前用户是商家
     *
     * @return
     */
    public Boolean loginUserIsBusiness() {
        User loginUser = getLoginUser();
        if (loginUser != null) {
            return RoleEnum.BUSINESS.getValue().equalsIgnoreCase(loginUser.getRole().getRole());
        }
        return false;
    }


    /**
     * 当前用户是客户
     *
     * @return
     */
    public Boolean loginUserIsCustomer() {
        User loginUser = getLoginUser();
        if (loginUser != null) {
            return RoleEnum.CUSTOMER.getValue().equalsIgnoreCase(loginUser.getRole().getRole());
        }
        return false;
    }


}
