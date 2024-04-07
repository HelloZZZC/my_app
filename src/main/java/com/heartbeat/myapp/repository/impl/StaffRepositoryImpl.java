package com.heartbeat.myapp.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.heartbeat.myapp.dao.dataobject.StaffDO;
import com.heartbeat.myapp.dao.mapper.StaffMapper;
import com.heartbeat.myapp.domain.model.Staff;
import com.heartbeat.myapp.dp.identifier.StaffId;
import com.heartbeat.myapp.repository.StaffRepository;
import com.heartbeat.myapp.repository.converter.StaffConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StaffRepositoryImpl implements StaffRepository {

    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private StaffConverter staffConverter;

    @Override
    public Staff get(StaffId staffId) {
        StaffDO staffDO = staffMapper.selectById(staffId.getValue());
        return staffConverter.toStaff(staffDO);
    }
}
