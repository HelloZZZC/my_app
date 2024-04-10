package com.heartbeat.myapp.repository.converter;

import com.heartbeat.myapp.dao.dataobject.DepartmentDO;
import com.heartbeat.myapp.domain.model.Department;
import com.heartbeat.myapp.dp.identifier.DepartmentId;
import org.springframework.stereotype.Component;

@Component
public class DepartmentConverter {

    /**
     * @param departmentDO 部门DO
     * @return 部门实体
     */
    public Department toDepartment(DepartmentDO departmentDO) {
        return new Department(
                new DepartmentId(departmentDO.getId()),
                departmentDO.getTitle(),
                new DepartmentId(departmentDO.getParentId()),
                departmentDO.getPath(),
                departmentDO.getLevel(),
                departmentDO.getSort(),
                departmentDO.getAbout(),
                departmentDO.getCreatorId(),
                departmentDO.getOperatorId(),
                departmentDO.getCreateTime(),
                departmentDO.getUpdateTime(),
                departmentDO.getIsDeleted()
        );
    }
}
