package com.heartbeat.myapp.exception.errorcode;

import com.heartbeat.myapp.exception.BizErrorCode;

/**
 * 通用错误码
 * 项目编码：01
 * 模块编码：05
 * 错误编号：0001 ~ 9999
 */
public enum CommonErrorCode implements BizErrorCode {

    INVALID_PARAMS("01050001", "非法的参数"),
    UN_KNOWN("01050002", "未知异常");

    private final String message;

    private final String code;

    CommonErrorCode(String code, String message) {
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
