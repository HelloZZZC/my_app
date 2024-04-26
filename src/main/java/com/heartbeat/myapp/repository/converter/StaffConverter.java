package com.heartbeat.myapp.repository.converter;

import com.heartbeat.myapp.dao.dataobject.StaffDO;
import com.heartbeat.myapp.domain.model.Staff;
import com.heartbeat.myapp.dp.StaffEmail;
import com.heartbeat.myapp.dp.StaffPhone;
import com.heartbeat.myapp.dp.identifier.StaffId;
import com.heartbeat.myapp.enums.StaffStatusEnum;
import org.springframework.stereotype.Component;

@Component
public class StaffConverter {

    /**
     * @param staffDO 职员DO
     * @return 职员实体
     */
    public Staff toStaff(StaffDO staffDO) {
        return new Staff(
                new StaffId(staffDO.getId()),
                staffDO.getNickname(),
                staffDO.getRealName(),
                new StaffEmail(staffDO.getEmail()),
                new StaffPhone(staffDO.getPhone()),
                staffDO.getAvatar(),
                staffDO.getGender(),
                StaffStatusEnum.toEnum(staffDO.getStatus()),
                staffDO.getRoleId(),
                staffDO.getDepartmentId(),
                staffDO.getCreatorId(),
                staffDO.getOperatorId(),
                staffDO.getCreateTime(),
                staffDO.getUpdateTime(),
                staffDO.getIsDeleted()
        );
    }
}
