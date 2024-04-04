package com.heartbeat.myapp.enums;

import java.util.stream.Stream;

public enum StaffStatusEnum {
    /**
     * 在职
     */
    EMPLOYMENT(1),

    /**
     * 离职
     */
    DEPART(0);

    private final Integer value;

    StaffStatusEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }

    public static StaffStatusEnum toEnum (Integer value) {
        return Stream.of(StaffStatusEnum.values()).filter(statusEnum -> statusEnum.getValue().equals(value))
                .findFirst().orElse(null);
    }
}
