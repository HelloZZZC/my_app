package com.heartbeat.myapp.exception.errorcode;

import com.heartbeat.myapp.exception.BizErrorCode;

/**
 * 部门相关错误码
 * 项目编码：01
 * 模块编码：02
 * 错误编号：0001 ~ 9999
 */
public enum DepartmentErrorCode implements BizErrorCode {

    DEPARTMENT_NOT_FOUND("01020001", "系统部门不存在");

    private final String message;

    private final String code;

    DepartmentErrorCode(String code, String message) {
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
