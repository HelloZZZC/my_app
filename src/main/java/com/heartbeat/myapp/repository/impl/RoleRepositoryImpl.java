package com.heartbeat.myapp.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.heartbeat.myapp.dao.dataobject.RoleDO;
import com.heartbeat.myapp.dao.dataobject.RolePermissionDO;
import com.heartbeat.myapp.dao.mapper.RoleMapper;
import com.heartbeat.myapp.dao.mapper.RolePermissionMapper;
import com.heartbeat.myapp.domain.model.Permission;
import com.heartbeat.myapp.domain.model.Role;
import com.heartbeat.myapp.dp.identifier.PermissionId;
import com.heartbeat.myapp.dp.identifier.RoleId;
import com.heartbeat.myapp.enums.DeletedEnum;
import com.heartbeat.myapp.repository.PermissionRepository;
import com.heartbeat.myapp.repository.RoleRepository;
import com.heartbeat.myapp.repository.converter.RoleConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RoleConverter roleConverter;

    @Override
    public Role get(RoleId roleId) {
        LambdaQueryWrapper<RoleDO> roleQueryMapper = new LambdaQueryWrapper<>();
        roleQueryMapper.eq(RoleDO::getId, roleId.getValue());
        roleQueryMapper.eq(RoleDO::getIsDeleted, DeletedEnum.NOT_DELETED.getValue());
        RoleDO roleDO = roleMapper.selectOne(roleQueryMapper);

        LambdaQueryWrapper<RolePermissionDO> rolePermissionQueryWrapper = new LambdaQueryWrapper<>();
        rolePermissionQueryWrapper.eq(RolePermissionDO::getRoleId, roleId.getValue());
        rolePermissionQueryWrapper.eq(RolePermissionDO::getIsDeleted, DeletedEnum.NOT_DELETED.getValue());
        List<RolePermissionDO> rolePermissionDOList = rolePermissionMapper.selectList(rolePermissionQueryWrapper);
        List<PermissionId> permissionIds = PermissionId.toList(Lists.transform(rolePermissionDOList, RolePermissionDO::getPermissionId));
        List<Permission> permissionList = permissionRepository.findBy(permissionIds);

        return roleConverter.toRole(roleDO, permissionList);
    }
}
