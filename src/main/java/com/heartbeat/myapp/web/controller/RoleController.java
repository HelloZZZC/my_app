package com.heartbeat.myapp.web.controller;

import com.heartbeat.myapp.util.ResponseUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @GetMapping(value = "/info")
    @RequiresPermissions(value = "sys:role:info")
    public ResponseUtil<String> info() {
        return ResponseUtil.success("测试权限");
    }
}
