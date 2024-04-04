package com.heartbeat.myapp.enums;

public enum DeletedEnum {
    /**
     * 未删除
     */
    NOT_DELETED(0),

    /**
     * 已删除
     */
    DELETED(1);

    private final Integer value;

    DeletedEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }

}
