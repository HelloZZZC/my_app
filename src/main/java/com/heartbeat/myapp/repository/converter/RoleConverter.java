package com.heartbeat.myapp.repository.converter;

import com.heartbeat.myapp.dao.dataobject.RoleDO;
import com.heartbeat.myapp.domain.model.Permission;
import com.heartbeat.myapp.domain.model.Role;
import com.heartbeat.myapp.dp.identifier.RoleId;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleConverter {

    /**
     * @param roleDO         角色DO
     * @param permissionList 权限实体列表
     * @return 角色实体
     */
    public Role toRole(RoleDO roleDO, List<Permission> permissionList) {
        return new Role(
                new RoleId(roleDO.getId()),
                roleDO.getTitle(),
                roleDO.getCode(),
                roleDO.getAbout(),
                permissionList,
                roleDO.getCreatorId(),
                roleDO.getOperatorId(),
                roleDO.getCreateTime(),
                roleDO.getUpdateTime(),
                roleDO.getIsDeleted()
        );
    }
}
