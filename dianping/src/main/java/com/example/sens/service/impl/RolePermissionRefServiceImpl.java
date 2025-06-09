package com.example.sens.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sens.entity.RolePermissionRef;
import com.example.sens.mapper.RolePermissionRefMapper;
import com.example.sens.service.RolePermissionRefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolePermissionRefServiceImpl extends ServiceImpl<RolePermissionRefMapper, RolePermissionRef>  implements RolePermissionRefService {

    @Autowired
    private RolePermissionRefMapper rolePermissionRefMapper;

    @Override
    public void deleteRefByRoleId(Long roleId) {
        rolePermissionRefMapper.deleteByRoleId(roleId);
    }

    @Override
    public void deleteByPermissionId(Long permissionId) {
        rolePermissionRefMapper.deleteByPermissionId(permissionId);
    }


    @Override
    public void batchSaveByRolePermissionRef(List<RolePermissionRef> rolePermissionRefs) {
        rolePermissionRefMapper.batchInsert(rolePermissionRefs);
    }
}