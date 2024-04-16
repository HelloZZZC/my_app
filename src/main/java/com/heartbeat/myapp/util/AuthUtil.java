package com.heartbeat.myapp.util;

import com.heartbeat.myapp.dp.identifier.StaffId;
import org.apache.shiro.SecurityUtils;
import org.springframework.util.ObjectUtils;

public class AuthUtil {

    /**
     * 获取当前登录的职工ID
     * @return StaffId
     */
    public static StaffId getCurrentStaffId() {
        Integer staffId = (Integer) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        return ObjectUtils.isEmpty(staffId) ? null : new StaffId(staffId);
    }
}
