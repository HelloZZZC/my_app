package com.heartbeat.myapp.web.param;


import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;

@Data
public class StaffCreateParam implements Serializable {

    @NotNull
    @Size(min = 1, max = 20, message = "昵称长度必须在1到20个字符之间")
    private String nickname;

    @NotNull
    @Size(min = 1, max = 20, message = "真实姓名长度必须在1到20个字符之间")
    private String realName;

    @NotNull
    @Size(min = 1, max = 128, message = "邮箱长度必须在1到128个字符之间")
    private String email;

    @Size(min = 1, max = 128, message = "头像链接长度必须在1到128个字符之间")
    private String avatar;

    @Max(value = 2, message = "性别属性必须为0-2")
    @NotNull
    private Integer gender;

    @Min(value = 1, message = "系统角色id必须是大于0的正整数")
    @NotNull
    private Integer roleId;

    @Min(value = 1, message = "系统部门id必须是大于0的正整数")
    @NotNull
    private Integer departmentId;
}
