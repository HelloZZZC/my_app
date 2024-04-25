package com.heartbeat.myapp.biz;

import com.heartbeat.myapp.dp.Username;
import com.heartbeat.myapp.dp.identifier.AccountId;
import com.heartbeat.myapp.dp.identifier.StaffId;

public interface AccountService {

    /**
     * 初始化职工账号
     *
     * @param staffId 职工ID
     * @param username 用户名
     */
    AccountId initStaffAccount(StaffId staffId, Username username);

    /**
     * 根据用户名查询账号ID
     *
     * @param username 用户名
     * @return 账号ID
     */
    AccountId getAccountIdBy(Username username);
}
