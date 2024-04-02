package com.heartbeat.myapp.web.model;

import lombok.Data;

@Data
public class ErrBodyVO {

    /**
     * 错误码
     */
    private String errCode;

    /**
     * 错误消息
     */
    private String errMsg;

    /**
     * Trace ID
     */
    private String traceId;
}
