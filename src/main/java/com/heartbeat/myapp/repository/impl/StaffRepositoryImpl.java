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
        LambdaQueryWrapper<StaffDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StaffDO::getId, staffId.getValue());
        StaffDO staffDO = staffMapper.selectOne(queryWrapper);

        return staffConverter.toStaff(staffDO);
    }
}
