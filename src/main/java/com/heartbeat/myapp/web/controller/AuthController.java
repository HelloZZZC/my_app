package com.heartbeat.myapp.web.controller;

import com.heartbeat.myapp.biz.AuthService;
import com.heartbeat.myapp.biz.StaffService;
import com.heartbeat.myapp.dp.identifier.StaffId;
import com.heartbeat.myapp.dto.StaffDTO;
import com.heartbeat.myapp.util.AuthUtil;
import com.heartbeat.myapp.util.ResponseUtil;
import com.heartbeat.myapp.web.model.AuthorizerVO;
import com.heartbeat.myapp.web.param.LoginParam;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private StaffService staffService;

    @PostMapping(value = "/login")
    public ResponseUtil<String> login(@RequestBody @Validated LoginParam loginParam) {
        return ResponseUtil.success(authService.login(loginParam));
    }

    @GetMapping(value = "/authorizerInfo")
    @RequiresAuthentication
    public ResponseUtil<AuthorizerVO> getAuthorizerInfo() {
        StaffId currentStaffId = Objects.requireNonNull(AuthUtil.getCurrentStaffId());
        StaffDTO staffDTO = staffService.getStaff(currentStaffId);
        return ResponseUtil.success(AuthorizerVO.transformBy(staffDTO));
    }
}
