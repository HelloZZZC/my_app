package com.heartbeat.myapp.dp.identifier;

import lombok.Getter;

@Getter
public class StaffId implements Identifier<Integer> {

    private final Integer value;

    public StaffId(Integer value) {
        this.value = value;
    }
}
