package com.heartbeat.myapp.web.model;

import com.heartbeat.myapp.dto.PermissionDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class StaffPermissionDisplayVO implements Serializable {

    private Integer id;

    private String title;

    private String code;

    /**
     * @param permissionDTO PermissionDTO
     * @return StaffPermissionDisplayVO
     */
    public static StaffPermissionDisplayVO transformBy(PermissionDTO permissionDTO) {
        StaffPermissionDisplayVO displayVO = new StaffPermissionDisplayVO();
        displayVO.setId(permissionDTO.getId());
        displayVO.setCode(permissionDTO.getCode());
        displayVO.setTitle(permissionDTO.getTitle());

        return displayVO;
    }

    /**
     * @param permissionDTOList List<PermissionDTO>
     * @return List<StaffPermissionDisplayVO>
     */
    public static List<StaffPermissionDisplayVO> transformListBy(List<PermissionDTO> permissionDTOList) {
        List<StaffPermissionDisplayVO> displayVOList = new ArrayList<>();
        permissionDTOList.forEach(permissionDTO -> {
            displayVOList.add(StaffPermissionDisplayVO.transformBy(permissionDTO));
        });

        return displayVOList;
    }
}
