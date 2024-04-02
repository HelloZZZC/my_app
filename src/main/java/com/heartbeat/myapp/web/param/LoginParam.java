package com.heartbeat.myapp.web.param;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.io.Serializable;

@Data
public class LoginParam implements Serializable {

    @NotNull(message = "用户名不允许为空")
    @NotBlank(message = "用户名不允许为空字符串")
    private String username;

    @NotNull(message = "密码不允许为空")
    @NotBlank(message = "密码不允许为空字符串")
    private String password;
}
