package com.heartbeat.myapp.dto;

import com.heartbeat.myapp.domain.model.Role;
import lombok.Data;
import org.springframework.beans.BeanUtils;

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

    private StaffBasicDTO creator;

    private StaffBasicDTO operator;

    private Date createTime;

    private Date updateTime;

    private Integer isDeleted;

    public static RoleDTO transformBy(Role role, StaffBasicDTO creator, StaffBasicDTO operator) {
        RoleDTO roleDTO = new RoleDTO();
        BeanUtils.copyProperties(role, roleDTO);
        roleDTO.setId(role.getId().getValue());
        roleDTO.setPermissions(PermissionDTO.transformListBy(role.getPermissions()));
        roleDTO.setCreator(creator);
        roleDTO.setOperator(operator);

        return roleDTO;
    }
}
