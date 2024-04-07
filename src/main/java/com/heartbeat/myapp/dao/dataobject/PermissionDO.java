package com.heartbeat.myapp.dao.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "permissions")
public class PermissionDO extends BaseDO implements Serializable {

    /**
     * 权限标题
     */
    private String title;

    /**
     * 编码
     * staff:view
     * permission:view
     */
    private String code;

    /**
     * 关于
     */
    private String about;

    /**
     * 创建人
     */
    private Integer creatorId;

    /**
     * 最后操作人
     */
    private Integer operatorId;
}
