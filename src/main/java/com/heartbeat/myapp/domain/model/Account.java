package com.heartbeat.myapp.domain.model;



import com.heartbeat.myapp.dp.Password;
import org.mindrot.jbcrypt.BCrypt;

import java.io.Serializable;
import java.util.Date;

public class Account implements Serializable {

    private final Integer id;

    private final String username;

    private final Password password;

    private final String salt;

    private final Integer staffId;

    private final Date createTime;

    private final Date updateTime;

    private final Integer isDeleted;

    public Account(
            Integer id,
            String username,
            Password password,
            String salt,
            Integer staffId,
            Date createTime,
            Date updateTime,
            Integer isDeleted
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.staffId = staffId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isDeleted = isDeleted;
    }

    /**
     * @param checkPassword
     */
    public void verifyPassword(Password checkPassword) {
        boolean isRight = BCrypt.checkpw(
                this.password.getPassword(),
                checkPassword.hash(this.salt).getPassword()
        );
        if (!isRight) {
            throw new RuntimeException();
        }
    }
}
