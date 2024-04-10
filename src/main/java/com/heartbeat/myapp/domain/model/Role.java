package com.heartbeat.myapp.domain.model;

import com.heartbeat.myapp.dp.identifier.RoleId;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Role implements Serializable {

    private RoleId id;

    private String title;

    private String code;

    private String about;

    private List<Permission> permissions;

    private Integer creatorId;

    private Integer operatorId;

    private Date createTime;

    private Date updateTime;

    private Integer isDeleted;
}
