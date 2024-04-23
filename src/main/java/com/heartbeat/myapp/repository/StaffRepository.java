package com.heartbeat.myapp.repository;

import com.heartbeat.myapp.dao.dataobject.StaffDO;
import com.heartbeat.myapp.domain.model.Staff;
import com.heartbeat.myapp.dp.identifier.StaffId;

public interface StaffRepository {

    /**
     * @param staffId 员工id
     * @return 员工实体
     */
    Staff get(StaffId staffId);

    /**
     * 插入单条职工
     *
     * @param staffDO 职工DO
     * @return 职工ID
     */
    StaffId insert(StaffDO staffDO);
}
