package com.heartbeat.myapp.domain.model;

import com.heartbeat.myapp.dp.identifier.PermissionId;

import java.util.Date;

public class Permission {

    private PermissionId id;

    private String title;

    private String code;

    private String about;

    private Integer creatorId;

    private Integer operatorId;

    private Date createTime;

    private Date updateTime;

    private Integer isDeleted;
}
