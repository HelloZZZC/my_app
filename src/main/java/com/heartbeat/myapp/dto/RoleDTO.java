package com.heartbeat.myapp.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class RoleDTO implements Serializable {

    private Integer id;

    private String title;

    private String code;

    private String about;

    private List<PermissionDTO> permissions;

    private StaffDTO creator;

    private StaffDTO operator;

    private Date createTime;

    private Date updateTime;

    private Integer isDeleted;
}
