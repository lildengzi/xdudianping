package com.example.sens.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.sens.entity.RolePermissionRef;

import java.util.List;


public interface RolePermissionRefService extends IService<RolePermissionRef> {

    /**
     * 删除某个角色的所有关联
     *
     * @param roleId 角色Id
     */
    void deleteRefByRoleId(Long roleId);

    /**
     * 根据权限Id删除
     *
     * @param permissionId 权限Id
     * @return 影响行数
     */
    void deleteByPermissionId(Long permissionId);

    /**
     * 批量添加
     *
     * @param rolePermissionRefs 列表
     */
    void batchSaveByRolePermissionRef(List<RolePermissionRef> rolePermissionRefs);

}
