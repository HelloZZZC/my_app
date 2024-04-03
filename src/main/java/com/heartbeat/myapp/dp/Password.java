package com.heartbeat.myapp.dp;

import org.mindrot.jbcrypt.BCrypt;

import java.io.Serializable;

public class Password implements Serializable {

    private String value;

    private static final int SALT_ROUNDS = 10; // 盐的强度

    public Password(String value) {
        this.value = value;
    }

    public Password hash() {
        this.value = BCrypt.hashpw(this.value, BCrypt.gensalt(SALT_ROUNDS));
        return this;
    }

    public Password hash(String salt) {
        this.value = BCrypt.hashpw(this.value, salt);
        return this;
    }

    public String getPassword() {
        return this.value;
    }
}
