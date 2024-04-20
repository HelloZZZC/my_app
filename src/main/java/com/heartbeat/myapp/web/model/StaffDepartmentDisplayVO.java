package com.heartbeat.myapp.web.model;

import com.heartbeat.myapp.dto.DepartmentDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class StaffDepartmentDisplayVO implements Serializable {

    private Integer id;

    private String title;

    /**
     * @param departmentDTO DepartmentDTO
     * @return StaffDepartmentDisplayVO
     */
    public static StaffDepartmentDisplayVO transformBy(DepartmentDTO departmentDTO) {
        StaffDepartmentDisplayVO displayVO = new StaffDepartmentDisplayVO();
        displayVO.setId(departmentDTO.getId());
        displayVO.setTitle(departmentDTO.getTitle());

        return displayVO;
    }
}
