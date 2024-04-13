package com.heartbeat.myapp.biz.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.heartbeat.myapp.biz.DepartmentService;
import com.heartbeat.myapp.constant.CommonConstant;
import com.heartbeat.myapp.constant.DepartmentConstant;
import com.heartbeat.myapp.domain.model.Department;
import com.heartbeat.myapp.dp.identifier.DepartmentId;
import com.heartbeat.myapp.dto.DepartmentDTO;
import com.heartbeat.myapp.repository.DepartmentRepository;
import com.heartbeat.myapp.util.RedissonCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private RedissonCacheUtil redissonCacheUtil;

    @Override
    public DepartmentDTO getDepartment(DepartmentId departmentId) {
        Department department = tryGetFromCache(departmentId);
        if (ObjectUtils.isEmpty(department)) {
            throw new RuntimeException();
        }
        return DepartmentDTO.toDepartmentDTO(department);
    }

    /**
     * @param departmentId 部门ID
     * @return 部门实体
     */
    private Department tryGetFromCache(DepartmentId departmentId) {
        String cacheKey = String.format(DepartmentConstant.DEPARTMENT_INFO_FORMAT, departmentId.getValue());
        String departmentCache = redissonCacheUtil.get(cacheKey, String.class);
        if (Objects.nonNull(departmentCache)) {
            return JSON.parseObject(departmentCache, Department.class);
        }
        Department department = repository.get(departmentId);
        redissonCacheUtil.set(cacheKey, JSONObject.toJSONString(department), CommonConstant.SECONDS_OF_ONE_DAY);

        return department;
    }
}
