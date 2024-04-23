package com.heartbeat.myapp.dp;

import com.heartbeat.myapp.biz.AccountService;
import com.heartbeat.myapp.util.SpringContextUtil;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class Username implements Serializable {

    private final String value;

    private final AccountService accountService;

    public Username(String value) {
        this.value = value;
        this.accountService = SpringContextUtil.getBean(AccountService.class);
    }

    public Boolean isExist() {
        return Boolean.FALSE;
    }
}
