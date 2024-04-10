package com.heartbeat.myapp.domain.model;

import com.heartbeat.myapp.dp.identifier.PermissionId;
import com.heartbeat.myapp.enums.DeletedEnum;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Getter
public class Permission implements Serializable {

    private final PermissionId id;

    private final String title;

    private final String code;

    private final String about;

    private final Integer creatorId;

    private final Integer operatorId;

    private final Date createTime;

    private final Date updateTime;

    private final Integer isDeleted;

    public Permission(
            PermissionId id,
            String title,
            String code,
            String about,
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
