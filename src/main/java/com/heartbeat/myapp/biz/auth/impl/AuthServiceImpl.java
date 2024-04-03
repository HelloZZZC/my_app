package com.heartbeat.myapp.biz.auth.impl;

import com.heartbeat.myapp.biz.auth.AuthService;
import com.heartbeat.myapp.domain.model.Account;
import com.heartbeat.myapp.repository.AccountRepository;
import com.heartbeat.myapp.web.param.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AccountRepository repository;

    @Override
    public String login(LoginParam param) {
        Account account = repository.getByUsername(param.getUsername());
        // 当前用户名账号校验密码
        // account.checkPassword(param.getPassword);

        // 校验通过则jwt创建token

        return "token";
    }
}
