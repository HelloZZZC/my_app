package com.heartbeat.myapp.dto;

import com.heartbeat.myapp.domain.model.Department;
import lombok.Data;
import org.springframework.beans.BeanUtils;

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

    public static DepartmentDTO transformBy(Department department) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        BeanUtils.copyProperties(department, departmentDTO);
        departmentDTO.setId(department.getId().getValue());

        return departmentDTO;
    }
}
