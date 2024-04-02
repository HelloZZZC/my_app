package com.heartbeat.myapp.biz.auth.impl;

import com.heartbeat.myapp.biz.auth.AuthService;
import com.heartbeat.myapp.web.param.LoginParam;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public String login(LoginParam param) {
        return null;
    }
}
