package com.heartbeat.myapp.exception.errorcode;

import com.heartbeat.myapp.exception.BizErrorCode;

/**
 * 权限相关错误码
 * 项目编码：01
 * 模块编码：01
 * 错误编号：0001 ~ 9999
 */
public enum AuthErrorCode implements BizErrorCode {

    AUTH_VERIFY_FAILURE("01010001", "身份验证失败"),
    ACCOUNT_NOT_FOUND("01010002", "系统账号不存在"),
    PASSWORD_CHECK_FAILURE("01010003", "密码校验失败，密码或用户名错误");

    private final String message;

    private final String code;

    AuthErrorCode(String code, String message) {
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
