package com.heartbeat.myapp.dp;

import lombok.Getter;
import org.mindrot.jbcrypt.BCrypt;

import java.io.Serializable;

@Getter
public class Password implements Serializable {

    private String value;

     public Password(String value) {
        this.value = value;
    }

    public Password hash(Salt salt) {
        this.value = BCrypt.hashpw(this.value, salt.getValue());
        return this;
    }
}
