package com.heartbeat.myapp.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.heartbeat.myapp.dao.dataobject.DepartmentDO;
import com.heartbeat.myapp.dao.mapper.DepartmentMapper;
import com.heartbeat.myapp.domain.model.Department;
import com.heartbeat.myapp.dp.identifier.DepartmentId;
import com.heartbeat.myapp.enums.DeletedEnum;
import com.heartbeat.myapp.repository.DepartmentRepository;
import com.heartbeat.myapp.repository.converter.DepartmentConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private DepartmentConverter departmentConverter;

    @Override
    public Department get(DepartmentId departmentId) {
        LambdaQueryWrapper<DepartmentDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DepartmentDO::getId, departmentId.getValue());
        queryWrapper.eq(DepartmentDO::getIsDeleted, DeletedEnum.NOT_DELETED.getValue());

        DepartmentDO departmentDO = departmentMapper.selectOne(queryWrapper);

        return departmentConverter.toDepartment(departmentDO);
    }
}
