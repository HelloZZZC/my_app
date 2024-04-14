package com.heartbeat.myapp.exception;

import lombok.Getter;

@Getter
public class BizException extends RuntimeException {

    private final BizErrorCode errorCode;

    private String customMessage;

    public BizException(BizErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BizException(BizErrorCode errorCode, String customMessage) {
        super(customMessage);
        this.errorCode = errorCode;
        this.customMessage = customMessage;
    }
}
