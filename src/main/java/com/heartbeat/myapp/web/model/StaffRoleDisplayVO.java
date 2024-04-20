package com.heartbeat.myapp.web.model;


import com.heartbeat.myapp.dto.RoleDTO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

@Data
public class StaffRoleDisplayVO implements Serializable {

    private Integer id;

    private String title;

    private String code;

    private List<StaffPermissionDisplayVO> permissions;


    public static StaffRoleDisplayVO transformBy(RoleDTO roleDTO) {
        StaffRoleDisplayVO displayVO = new StaffRoleDisplayVO();
        displayVO.setId(roleDTO.getId());
        displayVO.setCode(roleDTO.getCode());
        displayVO.setTitle(roleDTO.getTitle());
        displayVO.setPermissions(StaffPermissionDisplayVO.transformListBy(roleDTO.getPermissions()));

        return displayVO;
    }
}
