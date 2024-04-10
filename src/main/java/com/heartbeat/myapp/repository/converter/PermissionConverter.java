package com.heartbeat.myapp.repository.converter;

import com.heartbeat.myapp.dao.dataobject.PermissionDO;
import com.heartbeat.myapp.domain.model.Permission;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PermissionConverter {

    public List<Permission> toPermissionList(List<PermissionDO> permissionDOList) {
        List<Permission> permissionList = new ArrayList<>();
        permissionDOList.forEach(permissionDO -> {
            permissionList.add(toPermission(permissionDO));
        });

        return permissionList;
    }

    public Permission toPermission(PermissionDO permissionDO) {
        return null;
    }
}
