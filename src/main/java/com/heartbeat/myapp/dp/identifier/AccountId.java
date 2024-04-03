package com.heartbeat.myapp.dp.identifier;


import jakarta.validation.constraints.NotNull;

public class AccountId implements Identifier<Integer> {

    private final Integer value;

    public AccountId(@NotNull Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }
}
