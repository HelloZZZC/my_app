package com.heartbeat.myapp.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.heartbeat.myapp.dao.dataobject.PermissionDO;
import com.heartbeat.myapp.dao.mapper.PermissionMapper;
import com.heartbeat.myapp.domain.model.Permission;
import com.heartbeat.myapp.dp.identifier.PermissionId;
import com.heartbeat.myapp.enums.DeletedEnum;
import com.heartbeat.myapp.repository.PermissionRepository;
import com.heartbeat.myapp.repository.converter.PermissionConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PermissionRepositoryImpl  implements PermissionRepository {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private PermissionConverter permissionConverter;

    @Override
    public List<Permission> findBy(List<PermissionId> permissionIds) {
        LambdaQueryWrapper<PermissionDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(PermissionDO::getId, permissionIds);
        queryWrapper.eq(PermissionDO::getIsDeleted, DeletedEnum.NOT_DELETED.getValue());

        List<PermissionDO> permissionDOList = permissionMapper.selectList(queryWrapper);

        return permissionConverter.toPermissionList(permissionDOList);
    }
}
