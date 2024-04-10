package com.heartbeat.myapp.biz.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.heartbeat.myapp.biz.StaffService;
import com.heartbeat.myapp.constant.CommonConstant;
import com.heartbeat.myapp.constant.StaffConstant;
import com.heartbeat.myapp.domain.model.Staff;
import com.heartbeat.myapp.dp.identifier.StaffId;
import com.heartbeat.myapp.dto.StaffDTO;
import com.heartbeat.myapp.repository.StaffRepository;
import com.heartbeat.myapp.util.RedissonCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private RedissonCacheUtil redissonCacheUtil;

    @Override
    public StaffDTO getStaff(StaffId staffId) {
        Staff staff = tryGetFromCache(staffId);

        return new StaffDTO();
    }

    /**
     * 尝试从缓存中获取职工信息
     *
     * @param staffId 职工id
     * @return Staff
     */
    private Staff tryGetFromCache(StaffId staffId) {
        String cacheKey = String.format(StaffConstant.STAFF_INFO_FORMAT, staffId.getValue());
        String staffCache = redissonCacheUtil.get(cacheKey, String.class);
        if (Objects.nonNull(staffCache)) {
            return JSON.parseObject(staffCache, Staff.class);
        }
        Staff staff = staffRepository.get(staffId);
        redissonCacheUtil.set(cacheKey, JSONObject.toJSONString(staff), CommonConstant.SECONDS_OF_ONE_DAY);

        return staff;
    }
}
