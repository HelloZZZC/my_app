package com.heartbeat.myapp.domain.model;

import com.heartbeat.myapp.dp.identifier.DepartmentId;
import com.heartbeat.myapp.enums.DeletedEnum;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Getter
public class Department implements Serializable {

    private final DepartmentId id;

    private final String title;

    private final DepartmentId parentId;

    private final String path;

    private final Integer level;

    private final Integer sort;

    private final String about;

    private final Integer creatorId;

    private final Integer operatorId;

    private final Date createTime;

    private final Date updateTime;

    private final Integer isDeleted;

    public Department(
            DepartmentId id,
            String title,
            DepartmentId parentId,
            String path,
            Integer level,
            Integer sort,
            String about,
            Integer creatorId,
            Integer operatorId,
            Date createTime,
            Date updateTime,
            Integer isDeleted
    ) {
        this.id = id;
        this.title = title;
        this.parentId = parentId;
        this.path = path;
        this.level = level;
        this.sort = sort;
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
