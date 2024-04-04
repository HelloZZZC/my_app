package com.heartbeat.myapp.biz.auth.impl;

import com.heartbeat.myapp.biz.auth.AuthService;
import com.heartbeat.myapp.domain.model.Account;
import com.heartbeat.myapp.domain.model.Staff;
import com.heartbeat.myapp.dp.Password;
import com.heartbeat.myapp.dp.identifier.StaffId;
import com.heartbeat.myapp.repository.AccountRepository;
import com.heartbeat.myapp.repository.StaffRepository;
import com.heartbeat.myapp.web.param.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public String login(LoginParam param) {
        Account account = accountRepository.getByUsername(param.getUsername());
        if (ObjectUtils.isEmpty(account)) {
            throw new RuntimeException();
        }
        account.verifyPassword(new Password(param.getPassword()));
        Staff staff = staffRepository.get(new StaffId(account.getStaffId()));

        // 校验通过则jwt创建token

        return "token";
    }
}
