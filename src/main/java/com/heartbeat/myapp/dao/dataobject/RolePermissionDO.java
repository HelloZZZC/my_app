package com.heartbeat.myapp.dao.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "role_permissions")
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
