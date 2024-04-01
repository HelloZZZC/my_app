package com.heartbeat.myapp.dao.dataobject;

import java.io.Serializable;

public class RolePermissionDO extends BaseDO implements Serializable {

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 权限ID
     */
    private Integer permissionId;
}
