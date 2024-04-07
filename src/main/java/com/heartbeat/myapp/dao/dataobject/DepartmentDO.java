package com.heartbeat.myapp.dao.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "departments")
public class DepartmentDO extends BaseDO implements Serializable {

    /**
     * 部门标题
     */
    private String title;

    /**
     * 上级部门ID
     */
    private Integer parentId;

    /**
     * 上级部门路劲
     * 1/22
     */
    private String path;

    /**
     * 层级
     */
    private Integer level;

    /**
     * 排序
     */
    private Integer sort;

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
