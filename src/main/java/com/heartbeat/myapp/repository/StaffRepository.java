package com.heartbeat.myapp.repository;

import com.heartbeat.myapp.dao.dataobject.StaffDO;
import com.heartbeat.myapp.domain.model.Staff;
import com.heartbeat.myapp.dp.StaffEmail;
import com.heartbeat.myapp.dp.StaffPhone;
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

    /**
     * @param staffEmail 职工邮箱
     * @return 职工ID
     */
    StaffId getStaffIdBy(StaffEmail staffEmail);

    /**
     * @param staffPhone 职工手机号
     * @return 职工ID
     */
    StaffId getStaffIdBy(StaffPhone staffPhone);
}
