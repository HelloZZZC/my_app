package com.heartbeat.myapp.biz.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.heartbeat.myapp.biz.AccountService;
import com.heartbeat.myapp.biz.DepartmentService;
import com.heartbeat.myapp.biz.RoleService;
import com.heartbeat.myapp.biz.StaffService;
import com.heartbeat.myapp.constant.CommonConstant;
import com.heartbeat.myapp.constant.StaffConstant;
import com.heartbeat.myapp.dao.dataobject.StaffDO;
import com.heartbeat.myapp.domain.model.Staff;
import com.heartbeat.myapp.dp.StaffEmail;
import com.heartbeat.myapp.dp.StaffPhone;
import com.heartbeat.myapp.dp.Username;
import com.heartbeat.myapp.dp.identifier.AccountId;
import com.heartbeat.myapp.dp.identifier.DepartmentId;
import com.heartbeat.myapp.dp.identifier.RoleId;
import com.heartbeat.myapp.dp.identifier.StaffId;
import com.heartbeat.myapp.dto.DepartmentDTO;
import com.heartbeat.myapp.dto.RoleDTO;
import com.heartbeat.myapp.dto.StaffBasicDTO;
import com.heartbeat.myapp.dto.StaffDTO;
import com.heartbeat.myapp.enums.StaffStatusEnum;
import com.heartbeat.myapp.exception.BizException;
import com.heartbeat.myapp.exception.errorcode.StaffErrorCode;
import com.heartbeat.myapp.repository.StaffRepository;
import com.heartbeat.myapp.util.RedissonCacheUtil;
import com.heartbeat.myapp.web.param.StaffCreateParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository repository;

    @Autowired
    private RedissonCacheUtil redissonCacheUtil;

    @Autowired
    private RoleService roleService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private AccountService accountService;

    @Override
    public StaffDTO getStaff(StaffId staffId) {
        Staff staff = tryGetFromCache(staffId);
        if (ObjectUtils.isEmpty(staff)) {
            throw new BizException(StaffErrorCode.STAFF_NOT_FOUND, String.format("系统职工[id:%d]不存在",
                    staffId.getValue()));
        }
        CompletableFuture<RoleDTO> roleFuture = CompletableFuture.supplyAsync(() -> roleService.getRole(
                new RoleId(staff.getRoleId()))).exceptionally(e -> null);
        CompletableFuture<DepartmentDTO> departmentFuture = CompletableFuture.supplyAsync(() -> departmentService.
                getDepartment(new DepartmentId(staff.getDepartmentId()))).exceptionally(e -> null);
        CompletableFuture<StaffBasicDTO> creatorFuture = CompletableFuture.supplyAsync(() -> getStaffBasic(new StaffId(
                staff.getCreatorId()))).exceptionally(e -> null);
        CompletableFuture<StaffBasicDTO> operatorFuture = CompletableFuture.supplyAsync(() -> getStaffBasic(new StaffId(
                staff.getOperatorId()))).exceptionally(e -> null);
        CompletableFuture.allOf(roleFuture, departmentFuture, creatorFuture, operatorFuture).join();

        return StaffDTO.toStaffDTO(staff, roleFuture.join(), departmentFuture.join(), creatorFuture.join(),
                operatorFuture.join());
    }

    @Override
    public StaffBasicDTO getStaffBasic(StaffId staffId) {
        Staff staff = tryGetFromCache(staffId);
        if (ObjectUtils.isEmpty(staff)) {
            throw new BizException(StaffErrorCode.STAFF_NOT_FOUND, String.format("系统职工[id:%d]不存在",
                    staffId.getValue()));
        }
        return StaffBasicDTO.transformBy(staff);
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
        Staff staff = repository.get(staffId);
        redissonCacheUtil.set(cacheKey, JSONObject.toJSONString(staff), CommonConstant.SECONDS_OF_ONE_DAY);

        return staff;
    }

    @Override
    @Transactional(rollbackFor = BizException.class)
    public Integer createStaffByManager(StaffCreateParam createParam, StaffId managerId) {
        StaffEmail staffEmail = new StaffEmail(createParam.getEmail());
        if (staffEmail.isExist()) {
            throw new BizException(StaffErrorCode.STAFF_EMAIL_REPEAT);
        }
        StaffPhone staffPhone = new StaffPhone(createParam.getPhone());
        if (staffPhone.isExist()) {
            throw new BizException(StaffErrorCode.STAFF_PHONE_REPEAT);
        }

        StaffDO staffDO = new StaffDO();
        BeanUtils.copyProperties(createParam, staffDO);
        staffDO.setCreatorId(managerId.getValue());
        staffDO.setOperatorId(managerId.getValue());
        staffDO.setStatus(StaffStatusEnum.EMPLOYMENT.getValue());
        StaffId staffId = repository.insert(staffDO);

        Username username = new Username(createParam.getEmail());
        AccountId accountId = accountService.initStaffAccount(staffId, username);
        log.info("初始化职工：{}账号成功，账号ID：{}", staffId.getValue(), accountId.getValue());

        return staffId.getValue();
    }

    @Override
    public StaffId getStaffIdBy(StaffPhone staffPhone) {
        return repository.getStaffIdBy(staffPhone);
    }

    @Override
    public StaffId getStaffIdBy(StaffEmail staffEmail) {
        return repository.getStaffIdBy(staffEmail);
    }
}
