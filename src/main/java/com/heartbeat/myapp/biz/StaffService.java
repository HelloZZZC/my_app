package com.heartbeat.myapp.biz;

import com.heartbeat.myapp.dp.identifier.StaffId;
import com.heartbeat.myapp.dto.StaffBasicDTO;
import com.heartbeat.myapp.dto.StaffDTO;
import com.heartbeat.myapp.web.param.StaffCreateParam;

public interface StaffService {

    /**
     * 根据ID获取职员详情
     *
     * @param staffId 职员ID
     * @return 职员详情
     */
    StaffDTO getStaff(StaffId staffId);

    /**
     * 根据ID获取职员基础信息
     *
     * @param staffId 职员ID
     * @return 职员基础信息
     */
    StaffBasicDTO getStaffBasic(StaffId staffId);

    /**
     * 创建职员
     *
     * @param createParam 职工创建参数
     * @param managerId   管理者ID
     * @return 创建成功的职员ID
     */
    Integer createStaffByManager(StaffCreateParam createParam, StaffId managerId);
}
