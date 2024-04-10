package com.heartbeat.myapp.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DepartmentDTO implements Serializable {

    private Integer id;

    private String title;

    private Integer parentId;

    private String path;

    private Integer level;

    private Integer sort;

    private String about;

    private Integer creatorId;

    private Integer operatorId;

    private Date createTime;

    private Date updateTime;

    private Integer isDeleted;
}
