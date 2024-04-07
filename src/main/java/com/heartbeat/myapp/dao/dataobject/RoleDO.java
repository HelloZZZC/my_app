package com.heartbeat.myapp.dao.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "roles")
public class RoleDO extends BaseDO implements Serializable {

    /**
     * 标题
     */
    private String title;

    /**
     * 编码
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
