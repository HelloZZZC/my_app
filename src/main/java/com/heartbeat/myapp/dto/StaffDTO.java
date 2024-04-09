package com.heartbeat.myapp.dto;

import com.heartbeat.myapp.dp.identifier.StaffId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class StaffDTO implements Serializable {

    private StaffId id;

    private String nickname;

    private String email;

    private String avatar;

    private Integer gender;

    private Integer status;

    private RoleDTO roleDTO;

    private DepartmentDTO departmentDTO;

    private StaffDTO creator;

    private StaffDTO operator;

    private Date createTime;

    private Date updateTime;
}
