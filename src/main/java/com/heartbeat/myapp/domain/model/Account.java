package com.heartbeat.myapp.domain.model;

import com.heartbeat.myapp.dp.AccountId;
import com.heartbeat.myapp.repository.Aggregate;

public class Account implements Aggregate<AccountId> {
    @Override
    public AccountId getId() {
        return null;
    }
}
