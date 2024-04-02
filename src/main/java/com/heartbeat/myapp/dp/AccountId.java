package com.heartbeat.myapp.dp;

import com.heartbeat.myapp.repository.Identifier;

public class AccountId implements Identifier {

    private final Integer value;

    public AccountId(Integer value) {
        if (value <= 0) {

        }
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }
}
