package com.example.sens.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sens.entity.Permission;
import com.example.sens.mapper.PermissionMapper;
import com.example.sens.mapper.RolePermissionRefMapper;
import com.example.sens.service.PermissionService;
import com.example.sens.common.util.PermissionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 角色业务逻辑实现类
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>  implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolePermissionRefMapper rolePermissionRefMapper;


    @Override
    public List<Permission> listPermissionsByRoleId(Long roleId) {
        return permissionMapper.findByRoleId(roleId);
    }

    @Override
    public Set<String> findPermissionUrlsByUserId(Long userId) {
        List<Permission> permissions = permissionMapper.findPermissionByUserId(userId);
        Set<String> urls = permissions.stream().map(p -> p.getUrl()).collect(Collectors.toSet());
        return urls;
    }

    @Override
    public List<Permission> findPermissionTreeByUserIdAndResourceType(Long userId, String resourceType) {
        List<Permission> permissions = permissionMapper.findPermissionByUserIdAndResourceType(userId, resourceType);
        return PermissionUtil.getPermissionTree(permissions);
    }

    @Override
    public List<Permission> findPermissionByRoleId(Long roleId) {
        return permissionMapper.findPermissionByRoleId(roleId);
    }


    @Override
    public List<Permission> findPermissionListWithLevel() {
        List<Permission> permissionList = permissionMapper.selectList(null);
        permissionList = PermissionUtil.getPermissionList(permissionList);

        // 加空格以展示等级
        for (Permission permission : permissionList) {
            for (int i = 1; i < permission.getLevel(); i++) {
                permission.setName("&nbsp;&nbsp;&nbsp;&nbsp;"+permission.getName());
            }
        }
        return permissionList;

    }

    @Override
    public Integer countChildPermission(Long id) {
        return permissionMapper.countChildPermission(id);
    }

    @Override
    public Permission findByUrl(String url) {
        return permissionMapper.findByUrl(url);
    }

}
