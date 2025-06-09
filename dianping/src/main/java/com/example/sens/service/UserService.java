package com.example.sens.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.sens.entity.User;

/**
 * 用户业务逻辑接口
 */
public interface UserService extends IService<User> {

    /**
     * 根据账号获得用户
     *
     * @param userName 账号
     * @return 用户
     */
    User findByUserName(String userName);


    /**
     * 更新密码
     *
     * @param userId   用户Id
     * @param password 密码
     */
    void updatePassword(Long userId, String password);

}
