package com.example.sens.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.sens.entity.Role;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author example
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {


    /**
     * 根据用户Id获得角色
     *
     * @param userId 用户Id
     * @return 角色列表
     */
    Role findByUserId(Long userId);

    /**
     * 根据role获得角色
     *
     * @param role role
     * @return 角色列表
     */
    Role findByRole(String role);


    /**
     * 统计某个角色的用户数
     *
     * @param roleId 角色Id
     * @return 用户数
     */
    Integer countUserByRoleId(Long roleId);



    /**
     * 获得用户注册默认角色
     * @return
     */
    Role findDefaultRole();
}

