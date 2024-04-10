package com.heartbeat.myapp.domain.model;

import com.heartbeat.myapp.dp.identifier.RoleId;
import com.heartbeat.myapp.enums.DeletedEnum;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
public class Role implements Serializable {

    private final RoleId id;

    private final String title;

    private final String code;

    private final String about;

    private final List<Permission> permissions;

    private final Integer creatorId;

    private final Integer operatorId;

    private final Date createTime;

    private final Date updateTime;

    private final Integer isDeleted;

    public Role(
            RoleId id,
            String title,
            String code,
            String about,
            List<Permission> permissions,
            Integer creatorId,
            Integer operatorId,
            Date createTime,
            Date updateTime,
            Integer isDeleted
    ) {
        this.id = id;
        this.title = title;
        this.code = code;
        this.about = about;
        this.permissions = permissions;
        this.creatorId = creatorId;
        this.operatorId = operatorId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isDeleted = isDeleted;

        if (this.isDeleted.equals(DeletedEnum.DELETED.getValue())) {
            throw new RuntimeException();
        }
    }
}
