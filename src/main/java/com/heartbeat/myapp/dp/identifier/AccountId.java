package com.heartbeat.myapp.dp.identifier;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AccountId implements Identifier<Integer> {

    private final Integer value;

    public AccountId(@NotNull Integer value) {
        this.value = value;
    }
}
