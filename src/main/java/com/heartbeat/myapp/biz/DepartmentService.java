package com.heartbeat.myapp.biz;

import com.heartbeat.myapp.dp.identifier.DepartmentId;
import com.heartbeat.myapp.dto.DepartmentDTO;

public interface DepartmentService {

    /**
     * 根据ID获取部门详情
     *
     * @param departmentId 部门ID
     * @return 部门详情
     */
    DepartmentDTO getDepartment(DepartmentId departmentId);
}
