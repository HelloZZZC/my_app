package com.heartbeat.myapp.web.controller;

import com.heartbeat.myapp.biz.StaffService;
import com.heartbeat.myapp.dp.identifier.StaffId;
import com.heartbeat.myapp.util.AuthUtil;
import com.heartbeat.myapp.util.ResponseUtil;
import com.heartbeat.myapp.web.param.StaffCreateParam;
import jakarta.validation.Valid;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(value = "/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @GetMapping(value = "/info")
    @RequiresPermissions(value = "sys:staff:info")
    public ResponseUtil<String> info() {
        StaffId managerId = Objects.requireNonNull(AuthUtil.getCurrentStaffId());
        return ResponseUtil.success(String.format("当前登录的职工ID: %d", managerId.getValue()));
    }

    @PostMapping(value = "/create")
    @RequiresPermissions(value = "sys:staff:create")
    public ResponseUtil<Integer> create(@RequestBody @Valid StaffCreateParam createParam) {
        StaffId managerId = Objects.requireNonNull(AuthUtil.getCurrentStaffId());
        return ResponseUtil.success(staffService.createStaffByManager(createParam, managerId));
    }
}
