package com.heartbeat.myapp.repository;

import com.heartbeat.myapp.domain.model.Role;
import com.heartbeat.myapp.dp.identifier.RoleId;

public interface RoleRepository {

    /**
     * @param roleId 角色ID
     * @return 角色实体
     */
    Role get(RoleId roleId);
}
