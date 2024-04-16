package com.heartbeat.myapp.util;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseUtil<T> implements Serializable {

    private Boolean success;

    private T data;

    private ResponseUtil(T data) {
        this.data = data;
    }

    public static <T> ResponseUtil<T> success(T data) {
        ResponseUtil<T> responseUtil = new ResponseUtil<>(data);
        responseUtil.setSuccess(Boolean.TRUE);

        return responseUtil;
    }

    public static <T> ResponseUtil<T> failure(T data) {
        ResponseUtil<T> responseUtil = new ResponseUtil<>(data);
        responseUtil.setSuccess(Boolean.FALSE);

        return responseUtil;
    }
}
