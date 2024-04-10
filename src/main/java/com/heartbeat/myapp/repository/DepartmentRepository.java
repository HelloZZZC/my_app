package com.heartbeat.myapp.repository;

import com.heartbeat.myapp.domain.model.Department;
import com.heartbeat.myapp.dp.identifier.DepartmentId;

public interface DepartmentRepository {

    /**
     * 根据部门ID获取部门信息
     *
     * @param departmentId 部门ID
     * @return 部门信息
     */
    Department get(DepartmentId departmentId);
}
