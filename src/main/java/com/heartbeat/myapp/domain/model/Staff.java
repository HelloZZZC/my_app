package com.heartbeat.myapp.domain.model;

import com.heartbeat.myapp.dp.StaffEmail;
import com.heartbeat.myapp.dp.StaffPhone;
import com.heartbeat.myapp.dp.identifier.StaffId;
import com.heartbeat.myapp.enums.DeletedEnum;
import com.heartbeat.myapp.enums.StaffStatusEnum;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Getter
public class Staff implements Serializable {

    private final StaffId id;

    private final String nickname;

    private final String realName;

    private final StaffEmail email;

    private final StaffPhone phone;

    private final String avatar;

    private final Integer gender;

    private final StaffStatusEnum status;

    private final Integer roleId;

    private final Integer departmentId;

    private final Integer creatorId;

    private final Integer operatorId;

    private final Date createTime;

    private final Date updateTime;

    private final Integer isDeleted;

    public Staff(
            StaffId id,
            String nickname,
            String realName,
            StaffEmail email,
            StaffPhone phone,
            String avatar,
            Integer gender,
            StaffStatusEnum status,
            Integer roleId,
            Integer departmentId,
            Integer creatorId,
            Integer operatorId,
            Date createTime,
            Date updateTime,
            Integer isDeleted
    ) {
        this.id = id;
        this.nickname = nickname;
        this.realName = realName;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
        this.gender = gender;
        this.status = status;
        this.roleId = roleId;
        this.departmentId = departmentId;
        this.creatorId = creatorId;
        this.operatorId = operatorId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isDeleted = isDeleted;

        if (this.isDeleted.equals(DeletedEnum.DELETED.getValue())) {
            throw new RuntimeException();
        }
    }


    public Boolean isEmployment() {
        return this.status.equals(StaffStatusEnum.EMPLOYMENT);
    }
}
