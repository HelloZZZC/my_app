package com.heartbeat.myapp.repository;

import com.heartbeat.myapp.domain.model.Permission;
import com.heartbeat.myapp.dp.identifier.PermissionId;

import java.util.List;

public interface PermissionRepository {

    /**
     * 根据权限ID列表获取相应的权限
     *
     * @param permissionIds 权限ID列表
     * @return 权限列表
     */
    List<Permission> findBy(List<PermissionId> permissionIds);
}
