package com.heartbeat.myapp.biz.impl;

import com.heartbeat.myapp.biz.AccountService;
import com.heartbeat.myapp.constant.CommonConstant;
import com.heartbeat.myapp.dao.dataobject.AccountDO;
import com.heartbeat.myapp.dp.Password;
import com.heartbeat.myapp.dp.Salt;
import com.heartbeat.myapp.dp.Username;
import com.heartbeat.myapp.dp.identifier.AccountId;
import com.heartbeat.myapp.dp.identifier.StaffId;
import com.heartbeat.myapp.exception.BizException;
import com.heartbeat.myapp.exception.errorcode.AccountErrorCode;
import com.heartbeat.myapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repository;

    @Override
    public AccountId initStaffAccount(StaffId staffId, Username username) {
        if (username.isExist()) {
            throw new BizException(AccountErrorCode.USERNAME_REPEAT);
        }
        AccountDO accountDO = new AccountDO();
        accountDO.setStaffId(staffId.getValue());
        accountDO.setUsername(username.getValue());
        Salt salt = new Salt();
        accountDO.setPassword(new Password(CommonConstant.DEFAULT_ACCOUNT_PASSWORD).hash(salt).getValue());
        accountDO.setSalt(salt.getValue());

        return repository.insert(accountDO);
    }

    @Override
    public AccountId getAccountIdBy(Username username) {
        return repository.getAccountIdBy(username);
    }
}
