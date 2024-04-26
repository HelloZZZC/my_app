package com.heartbeat.myapp.web.model;

import com.heartbeat.myapp.dto.StaffDTO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@Data
public class AuthorizerVO implements Serializable {

    private Integer id;

    private String nickname;

    private String realName;

    private String email;

    private String phone;

    private String avatar;

    private Integer gender;

    private StaffDepartmentDisplayVO department;

    private StaffRoleDisplayVO role;

    /**
     * @param staffDTO StaffDTO
     * @return AuthorizerVO
     */
    public static AuthorizerVO transformBy(StaffDTO staffDTO) {
        AuthorizerVO authorizerVO = new AuthorizerVO();
        BeanUtils.copyProperties(staffDTO, authorizerVO);
        authorizerVO.setDepartment(StaffDepartmentDisplayVO.transformBy(staffDTO.getDepartmentDTO()));
        authorizerVO.setRole(StaffRoleDisplayVO.transformBy(staffDTO.getRoleDTO()));

        return authorizerVO;
    }
}
