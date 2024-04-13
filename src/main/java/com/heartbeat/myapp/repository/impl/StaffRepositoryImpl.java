package com.heartbeat.myapp.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.heartbeat.myapp.dao.dataobject.StaffDO;
import com.heartbeat.myapp.dao.mapper.StaffMapper;
import com.heartbeat.myapp.domain.model.Staff;
import com.heartbeat.myapp.dp.identifier.StaffId;
import com.heartbeat.myapp.enums.DeletedEnum;
import com.heartbeat.myapp.repository.StaffRepository;
import com.heartbeat.myapp.repository.converter.StaffConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

@Repository
public class StaffRepositoryImpl implements StaffRepository {

    @Autowired
    private StaffMapper mapper;

    @Autowired
    private StaffConverter converter;

    @Override
    public Staff get(StaffId staffId) {
        LambdaQueryWrapper<StaffDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StaffDO::getId, staffId.getValue());
        queryWrapper.eq(StaffDO::getIsDeleted, DeletedEnum.NOT_DELETED.getValue());

        StaffDO staffDO = mapper.selectOne(queryWrapper);

        return ObjectUtils.isEmpty(staffDO) ? null : converter.toStaff(staffDO);
    }
}
