package com.heartbeat.myapp.exception.errorcode;

import com.heartbeat.myapp.exception.BizErrorCode;

/**
 * 账号相关错误码
 * 项目编码：01
 * 模块编码：06
 * 错误编号：0001 ~ 9999
 */
public enum AccountErrorCode implements BizErrorCode {

    USERNAME_REPEAT("01060001", "存在重复的用户名");

    private final String message;

    private final String code;

    AccountErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
