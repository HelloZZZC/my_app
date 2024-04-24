package com.heartbeat.myapp.biz.impl;

import com.heartbeat.myapp.biz.AccountService;
import com.heartbeat.myapp.dp.Username;
import com.heartbeat.myapp.dp.identifier.AccountId;
import com.heartbeat.myapp.dp.identifier.StaffId;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Override
    public void initStaffAccount(StaffId staffId, Username username) {

    }

    @Override
    public AccountId getAccountIdBy(Username username) {
        return null;
    }
}
