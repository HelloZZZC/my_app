package com.heartbeat.myapp.exception.errorcode;

import com.heartbeat.myapp.exception.BizErrorCode;

/**
 * 部门相关错误码
 * 项目编码：01
 * 模块编码：03
 * 错误编号：0001 ~ 9999
 */
public enum StaffErrorCode implements BizErrorCode {

    STAFF_NOT_FOUND("01030001", "系统职工不存在"),
    STAFF_NOT_EMPLOYMENT("01030002", "系统职工状态非在职");

    private final String message;

    private final String code;

    StaffErrorCode(String code, String message) {
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
