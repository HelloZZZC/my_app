package com.heartbeat.myapp.biz;

import com.heartbeat.myapp.dp.identifier.StaffId;
import com.heartbeat.myapp.dto.StaffDTO;

public interface StaffService {

    /**
     * 根据ID获取职员详情
     *
     * @param id 职员ID
     * @return 职员详情
     */
    StaffDTO getStaff(StaffId id);
}
