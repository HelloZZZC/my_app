package com.heartbeat.myapp.dp.identifier;

public class StaffId implements Identifier<Integer> {

    private final Integer value;

    public StaffId(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return null;
    }
}
