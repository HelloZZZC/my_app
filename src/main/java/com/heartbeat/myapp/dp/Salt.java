package com.heartbeat.myapp.dp;

import lombok.Getter;
import org.mindrot.jbcrypt.BCrypt;

import java.io.Serializable;

@Getter
public class Salt implements Serializable {

    private final String value;

    private static final int SALT_ROUNDS = 10; // 盐的强度

    public Salt() {
        this.value = BCrypt.gensalt(SALT_ROUNDS);
    }

    public Salt(String value) {
        this.value = value;
    }
}
