package com.heartbeat.myapp.repository.converter;

import com.heartbeat.myapp.dao.dataobject.PermissionDO;
import com.heartbeat.myapp.domain.model.Permission;
import com.heartbeat.myapp.dp.identifier.PermissionId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PermissionConverter {

    /**
     * @param permissionDOList 权限DO列表
     * @return 权限实体列表
     */
    public List<Permission> toPermissionList(List<PermissionDO> permissionDOList) {
        List<Permission> permissionList = new ArrayList<>();
        permissionDOList.forEach(permissionDO -> {
            permissionList.add(toPermission(permissionDO));
        });

        return permissionList;
    }

    /**
     * @param permissionDO 权限DO
     * @return 权限实体
     */
    public Permission toPermission(PermissionDO permissionDO) {
        return new Permission(
                new PermissionId(permissionDO.getId()),
                permissionDO.getTitle(),
                permissionDO.getCode(),
                permissionDO.getAbout(),
                permissionDO.getCreatorId(),
                permissionDO.getOperatorId(),
                permissionDO.getCreateTime(),
                permissionDO.getUpdateTime(),
                permissionDO.getIsDeleted()
        );
    }
}
