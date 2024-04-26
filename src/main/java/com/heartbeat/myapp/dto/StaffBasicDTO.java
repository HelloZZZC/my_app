package com.heartbeat.myapp.dto;

import com.heartbeat.myapp.domain.model.Staff;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

@Data
public class StaffBasicDTO implements Serializable {

    private Integer id;

    private String nickname;

    private String realName;

    private String email;

    private String phone;

    private String avatar;

    private Integer gender;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public static StaffBasicDTO transformBy(Staff staff) {
        StaffBasicDTO staffBasicDTO = new StaffBasicDTO();
        BeanUtils.copyProperties(staff, staffBasicDTO);
        staffBasicDTO.setId(staff.getId().getValue());
        staffBasicDTO.setStatus(staff.getStatus().getValue());

        return staffBasicDTO;
    }
}
