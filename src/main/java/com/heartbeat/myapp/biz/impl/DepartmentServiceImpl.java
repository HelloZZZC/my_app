package com.heartbeat.myapp.biz.impl;

import com.heartbeat.myapp.biz.DepartmentService;
import com.heartbeat.myapp.dp.identifier.DepartmentId;
import com.heartbeat.myapp.dto.DepartmentDTO;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Override
    public DepartmentDTO getDepartment(DepartmentId departmentId) {
        return null;
    }
}
