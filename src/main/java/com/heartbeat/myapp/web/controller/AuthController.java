package com.heartbeat.myapp.web.controller;

import com.heartbeat.myapp.biz.auth.AuthService;
import com.heartbeat.myapp.util.ResponseUtil;
import com.heartbeat.myapp.web.param.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/login")
    public ResponseUtil<String> login(@RequestBody @Validated LoginParam loginParam) {
        return ResponseUtil.success("sss");
    }
}
