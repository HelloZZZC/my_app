package com.heartbeat.myapp.web.controller;

import com.heartbeat.myapp.dp.identifier.StaffId;
import com.heartbeat.myapp.util.AuthUtil;
import com.heartbeat.myapp.util.ResponseUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(value = "/staff")
public class StaffController {

    @GetMapping(value = "/info")
    @RequiresPermissions(value = "sys:staff:info")
    public ResponseUtil<String> info() {
        StaffId currentStaffId = Objects.requireNonNull(AuthUtil.getCurrentStaffId());
        return ResponseUtil.success(String.format("当前登录的职工ID: %d", currentStaffId.getValue()));
    }
}
