package com.heartbeat.myapp.dao.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "staffs")
public class StaffDO extends BaseDO implements Serializable {

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 真名
     */
    private String realName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 所在部门ID
     */
    private Integer departmentId;

    /**
     * 创建人
     */
    private Integer creatorId;

    /**
     * 最后操作人
     */
    private Integer operatorId;
}
