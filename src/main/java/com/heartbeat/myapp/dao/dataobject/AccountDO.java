package com.heartbeat.myapp.dao.dataobject;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountDO extends BaseDO implements Serializable {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 关联职工ID
     */
    private Integer staffId;
}
