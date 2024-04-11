package com.heartbeat.myapp.dto;

import com.heartbeat.myapp.domain.model.Permission;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Data
public class PermissionDTO implements Serializable {

    private Integer id;

    private String title;

    private String code;

    private String about;

    private Integer creatorId;

    private Integer operatorId;

    private Date createTime;

    private Date updateTime;

    private Integer isDeleted;

    /**
     *
     * @param permission
     * @return
     */
    public static PermissionDTO toPermissionDTO(Permission permission) {
        PermissionDTO permissionDTO = new PermissionDTO();
        BeanUtils.copyProperties(permission, permissionDTO);
        permissionDTO.setId(permission.getId().getValue());

        return permissionDTO;
    }

    /**
     *
     * @param permissionList
     * @return
     */
    public static List<PermissionDTO> toPermissionDTOList(List<Permission> permissionList) {
        List<PermissionDTO> permissionDTOList = new ArrayList<>();
        if (CollectionUtils.isEmpty(permissionList)) {
            return Collections.emptyList();
        }
        permissionList.forEach(permission -> {
            PermissionDTO permissionDTO = toPermissionDTO(permission);
            permissionDTOList.add(permissionDTO);
        });

        return permissionDTOList;
    }
}
