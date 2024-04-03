package com.heartbeat.myapp.repository.converter;

import com.heartbeat.myapp.dao.dataobject.AccountDO;
import com.heartbeat.myapp.domain.model.Account;
import com.heartbeat.myapp.dp.Password;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter {

    public Account toAccount(AccountDO accountDO) {
        return new Account(
                accountDO.getId(),
                accountDO.getUsername(),
                new Password(accountDO.getPassword()),
                accountDO.getSalt(),
                accountDO.getStaffId(),
                accountDO.getCreateTime(),
                accountDO.getUpdateTime(),
                accountDO.getIsDeleted()
        );
    }
}
