package com.heartbeat.myapp.domain.model;



import com.heartbeat.myapp.dp.Password;
import com.heartbeat.myapp.dp.Salt;
import com.heartbeat.myapp.dp.identifier.AccountId;
import com.heartbeat.myapp.enums.DeletedEnum;
import lombok.Getter;
import org.mindrot.jbcrypt.BCrypt;

import java.io.Serializable;
import java.util.Date;

@Getter
public class Account implements Serializable {

    private final AccountId id;

    private final String username;

    private final Password password;

    private final Salt salt;

    private final Integer staffId;

    private final Date createTime;

    private final Date updateTime;

    private final Integer isDeleted;

    public Account(
            AccountId id,
            String username,
            Password password,
            Salt salt,
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

        if (isDeleted() || unboundStaff()) {
            throw new RuntimeException();
        }
    }

    /**
     * @param checkPassword 待校验的密码
     */
    public Boolean verifyPassword(Password checkPassword) {
        return BCrypt.checkpw(
                this.password.getValue(),
                checkPassword.hash(this.salt).getValue()
        );
    }

    private Boolean isDeleted() {
        return this.isDeleted.equals(DeletedEnum.DELETED.getValue());
    }

    private Boolean unboundStaff() {
        return this.staffId == null || this.staffId.equals(0);
    }
}
