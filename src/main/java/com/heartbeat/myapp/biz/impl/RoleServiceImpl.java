package com.heartbeat.myapp.biz.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.heartbeat.myapp.biz.RoleService;
import com.heartbeat.myapp.constant.CommonConstant;
import com.heartbeat.myapp.constant.RoleConstant;
import com.heartbeat.myapp.domain.model.Role;
import com.heartbeat.myapp.dp.identifier.RoleId;
import com.heartbeat.myapp.dto.RoleDTO;
import com.heartbeat.myapp.repository.RoleRepository;
import com.heartbeat.myapp.util.RedissonCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RedissonCacheUtil redissonCacheUtil;

    @Override
    public RoleDTO getRole(RoleId roleId) {
        Role role = tryGetFromCache(roleId);
        return new RoleDTO();
    }

    /**
     * @param roleId 角色ID
     * @return 角色实体
     */
    private Role tryGetFromCache(RoleId roleId) {
        String cacheKey = String.format(RoleConstant.ROLE_INFO_FORMAT, roleId.getValue());
        String roleCache = redissonCacheUtil.get(cacheKey, String.class);
        if (Objects.nonNull(roleCache)) {
            return JSON.parseObject(roleCache, Role.class);
        }
        Role role = roleRepository.get(roleId);
        redissonCacheUtil.set(cacheKey, JSONObject.toJSONString(role), CommonConstant.SECONDS_OF_ONE_DAY);

        return role;
    }
}
