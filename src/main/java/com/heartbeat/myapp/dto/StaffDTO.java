package com.heartbeat.myapp.dto;

import com.heartbeat.myapp.domain.model.Staff;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@Data
public class StaffDTO extends StaffBasicDTO implements Serializable {

    private RoleDTO roleDTO;

    private DepartmentDTO departmentDTO;

    private StaffBasicDTO creator;

    private StaffBasicDTO operator;

    public static StaffDTO toStaffDTO(
            Staff staff,
            RoleDTO roleDTO,
            DepartmentDTO departmentDTO,
            StaffBasicDTO creator,
            StaffBasicDTO operator) {
        StaffDTO staffDTO = new StaffDTO();
        BeanUtils.copyProperties(staff, staffDTO);
        staffDTO.setId(staff.getId().getValue());
        staffDTO.setRoleDTO(roleDTO);
        staffDTO.setDepartmentDTO(departmentDTO);
        staffDTO.setCreator(creator);
        staffDTO.setOperator(operator);

        return staffDTO;
    }
}
