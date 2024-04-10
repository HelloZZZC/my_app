package com.heartbeat.myapp.repository.converter;

import com.heartbeat.myapp.dao.dataobject.RoleDO;
import com.heartbeat.myapp.domain.model.Permission;
import com.heartbeat.myapp.domain.model.Role;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleConverter {

    public Role toRole(RoleDO roleDO, List<Permission> permissionList) {
        return null;
    }
}
