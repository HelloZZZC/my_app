package com.heartbeat.myapp.web.controller;

import com.heartbeat.myapp.util.ResponseUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/staff")
public class StaffController {

    @GetMapping(value = "/info")
    @RequiresPermissions(value = "sys:staff:info")
    public ResponseUtil<String> info() {
        return ResponseUtil.success("测试权限");
    }
}
