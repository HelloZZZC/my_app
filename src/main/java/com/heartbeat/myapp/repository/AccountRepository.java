package com.heartbeat.myapp.repository;


import com.heartbeat.myapp.dao.dataobject.AccountDO;
import com.heartbeat.myapp.domain.model.Account;
import com.heartbeat.myapp.dp.Username;
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
    Account getByUsername(Username username);

    /**
     * @param username 用户名
     * @return 账号ID
     */
    AccountId getAccountIdBy(Username username);

    /**
     * @param accountDO 账号DO
     * @return 插入账号ID
     */
    AccountId insert(AccountDO accountDO);
}
