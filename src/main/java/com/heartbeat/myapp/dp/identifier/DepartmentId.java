package com.heartbeat.myapp.dp.identifier;

import lombok.Getter;

@Getter
public class DepartmentId implements Identifier<Integer> {

    private final Integer value;

    public DepartmentId(Integer value) {
        this.value = value;
    }
}
