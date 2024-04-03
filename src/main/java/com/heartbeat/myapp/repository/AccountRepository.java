package com.heartbeat.myapp.repository;


import com.heartbeat.myapp.domain.model.Account;
import com.heartbeat.myapp.dp.identifier.AccountId;

public interface AccountRepository {

    /**
     * @param accountId 账号ID
     * @return 账号实体
     */
    Account get(AccountId accountId);

    /**
     * @param username 用户名
     * @return 账号实体
     */
    Account getByUsername(String username);
}
