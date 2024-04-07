package com.heartbeat.myapp.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heartbeat.myapp.dao.dataobject.DepartmentDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentMapper extends BaseMapper<DepartmentDO> {
}
