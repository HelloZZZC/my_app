package com.heartbeat.myapp.repository;

import com.heartbeat.myapp.domain.model.Staff;
import com.heartbeat.myapp.dp.identifier.StaffId;

public interface StaffRepository {

    /**
     * @param staffId 员工id
     * @return 员工实体
     */
    Staff get(StaffId staffId);
}
