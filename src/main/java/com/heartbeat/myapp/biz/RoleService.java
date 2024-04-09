package com.heartbeat.myapp.biz;

import com.heartbeat.myapp.dp.identifier.RoleId;
import com.heartbeat.myapp.dto.RoleDTO;

public interface RoleService {

    /**
     * 根据ID获取角色详情
     *
     * @param roleId 角色ID
     * @return 角色详情
     */
    RoleDTO getRole(RoleId roleId);
}
