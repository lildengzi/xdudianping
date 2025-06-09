package com.example.sens.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sens.entity.Permission;
import com.example.sens.entity.Role;
import com.example.sens.entity.RolePermissionRef;
import com.example.sens.mapper.RoleMapper;
import com.example.sens.service.RolePermissionRefService;
import com.example.sens.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 角色业务逻辑实现类
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionRefService rolePermissionRefService;

    @Override
    public Role findByRoleId(Long roleId) {
        return roleMapper.selectById(roleId);
    }

    @Override
    public Role findByUserId(Long userId) {
        return roleMapper.findByUserId(userId);
    }

    @Override
    public Role findByRole(String role) {
        return roleMapper.findByRole(role);
    }

    @Override
    public Integer countUserByRoleId(Long roleId) {
        return roleMapper.countUserByRoleId(roleId);
    }

    @Override
    public Role findDefaultRole() {
        Role role = roleMapper.findDefaultRole();
        if (role == null) {
            role = new Role();
            role.setRole("customer");
            role.setDescription("客户");
            role.setIsRegisterDefault(1);
            role.setCreateTime(new Date());
            roleMapper.insert(role);
        }
        return role;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(Role role) {
        roleMapper.insert(role);
        if (role.getPermissions() != null && role.getPermissions().size() > 0) {
            List<RolePermissionRef> rolePermissionRefs = new ArrayList<>(role.getPermissions().size());
            for (Permission permission : role.getPermissions()) {
                rolePermissionRefs.add(new RolePermissionRef(role.getId(), permission.getId()));
            }
            rolePermissionRefService.batchSaveByRolePermissionRef(rolePermissionRefs);
        }
        return true;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(Role role) {
        roleMapper.updateById(role);
        if (role.getPermissions() != null && role.getPermissions().size() > 0) {
            // 删除关联
            rolePermissionRefService.deleteRefByRoleId(role.getId());
            // 添加关联
            List<RolePermissionRef> rolePermissionRefs = new ArrayList<>(role.getPermissions().size());
            for (Permission permission : role.getPermissions()) {
                rolePermissionRefs.add(new RolePermissionRef(role.getId(), permission.getId()));
            }
            rolePermissionRefService.batchSaveByRolePermissionRef(rolePermissionRefs);
        }
        return true;
    }

}
