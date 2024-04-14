package com.heartbeat.myapp.biz.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.heartbeat.myapp.biz.RoleService;
import com.heartbeat.myapp.biz.StaffService;
import com.heartbeat.myapp.constant.CommonConstant;
import com.heartbeat.myapp.constant.RoleConstant;
import com.heartbeat.myapp.domain.model.Role;
import com.heartbeat.myapp.dp.identifier.RoleId;
import com.heartbeat.myapp.dp.identifier.StaffId;
import com.heartbeat.myapp.dto.RoleDTO;
import com.heartbeat.myapp.dto.StaffBasicDTO;
import com.heartbeat.myapp.exception.BizException;
import com.heartbeat.myapp.exception.errorcode.RoleErrorCode;
import com.heartbeat.myapp.repository.RoleRepository;
import com.heartbeat.myapp.util.RedissonCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository repository;

    @Autowired
    private RedissonCacheUtil redissonCacheUtil;

    @Autowired
    private StaffService staffService;

    @Override
    public RoleDTO getRole(RoleId roleId) {
        Role role = tryGetFromCache(roleId);
        if (ObjectUtils.isEmpty(role)) {
            throw new BizException(RoleErrorCode.ROLE_NOT_FOUND, String.format("系统角色[id:%d]不存在",
                    roleId.getValue()));
        }
        CompletableFuture<StaffBasicDTO> creatorFuture = CompletableFuture.supplyAsync(() -> staffService
                .getStaffBasic(new StaffId(role.getCreatorId()))).exceptionally(e -> null);
        CompletableFuture<StaffBasicDTO> operatorFuture = CompletableFuture.supplyAsync(() -> staffService
                .getStaffBasic(new StaffId(role.getOperatorId()))).exceptionally(e -> null);
        CompletableFuture.allOf(creatorFuture, operatorFuture).join();

        return RoleDTO.toRoleDTO(role, creatorFuture.join(), operatorFuture.join());
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
        Role role = repository.get(roleId);
        redissonCacheUtil.set(cacheKey, JSONObject.toJSONString(role), CommonConstant.SECONDS_OF_ONE_DAY);

        return role;
    }
}
