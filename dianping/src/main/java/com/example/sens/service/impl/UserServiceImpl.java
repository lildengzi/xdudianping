package com.example.sens.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuyanzhao.yztool.common.exception.MyBusinessException;
import com.example.sens.mapper.UserMapper;
import com.example.sens.entity.User;
import com.example.sens.service.*;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 用户业务逻辑实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>  implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleService roleService;


    @Override
    public User findByUserName(String userName) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name", userName);
        return userMapper.selectOne(queryWrapper);
    }


    @Override
    public void updatePassword(Long userId, String password) {
        User user = new User();
        user.setId(userId);
        user.setUserPass(password);
        userMapper.updateById(user);
    }

    @Override
    public boolean save(User user) {
        // 1.检查长度
        basicCheck(user);
        // 2.验证账号和身份证号码是否存在
        checkUnique(user);
        // 3.添加
        if(user.getUserAvatar() == null) {
            user.setUserAvatar("/static/images/avatar/" + RandomUtils.nextInt(1, 41) + ".jpeg");
        }
        userMapper.insert(user);
        return true;
    }

    @Override
    public boolean updateById(User user) {
        // 1.检查长度
        basicCheck(user);
        // 2.验证唯一性
        checkUnique(user);
        // 3.更新
        userMapper.updateById(user);
        return true;
    }

    private void checkUnique(User user) {
        //验证账号是否存在
        if (user.getUserName() != null) {
            User nameCheck = findByUserName(user.getUserName());
            Boolean isExist = (user.getId() == null && nameCheck != null) ||
                    (user.getId() != null && nameCheck != null && !Objects.equals(nameCheck.getId(), user.getId()));
            if (isExist) {
                throw new MyBusinessException("账号已经存在");
            }
        }
        //验证账号和身份证号码是否存在
        if (user.getPhone() != null) {
            User check = findByUserName(user.getPhone());
            Boolean isExist = (user.getId() == null && check != null) ||
                    (user.getId() != null && check != null && !Objects.equals(check.getId(), user.getId()));
            if (isExist) {
                throw new MyBusinessException("手机号已经存在");
            }
        }

    }

    private void basicCheck(User user) {
        String userName = user.getUserName();
        String userDisplayName = user.getUserDisplayName();
        // 2.账号码长度是否合法
        if (StringUtils.isNotEmpty(userName) && (userName.length() > 20 || userName.length() < 2)) {
            throw new MyBusinessException("账号不合法，请输入2-20位");
        }
        // 3.姓名长度是否合法
        if (StringUtils.isNotEmpty(userDisplayName) && (userDisplayName.length() > 20 || userDisplayName.length() < 2)) {
            throw new MyBusinessException("姓名长度不合法，请输入2-20位");
        }
    }

}
