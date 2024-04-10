package com.heartbeat.myapp.repository.impl;

import com.heartbeat.myapp.domain.model.Department;
import com.heartbeat.myapp.dp.identifier.DepartmentId;
import com.heartbeat.myapp.repository.DepartmentRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository {
    @Override
    public Department get(DepartmentId departmentId) {
        return null;
    }
}
