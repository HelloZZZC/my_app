package com.heartbeat.myapp.dp.identifier;

import lombok.Getter;

@Getter
public class RoleId implements Identifier<Integer> {

    private final Integer value;

    public RoleId(Integer value) {
        this.value = value;
    }
}
