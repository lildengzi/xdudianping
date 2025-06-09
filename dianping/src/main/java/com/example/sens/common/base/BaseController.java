package com.example.sens.common.base;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * 基础控制器
 */
public class BaseController {

    @Autowired
    protected HttpServletRequest request;

    /**
     * 渲染404页面
     */
    protected String renderNotFound() {
        return "error/404";
    }

    /**
     * 渲染403页面
     */
    protected String renderError() {
        return "error/403";
    }

} 