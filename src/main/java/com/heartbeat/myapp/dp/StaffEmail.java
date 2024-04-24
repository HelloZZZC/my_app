package com.heartbeat.myapp.dp;

import com.heartbeat.myapp.biz.StaffService;
import com.heartbeat.myapp.dp.identifier.StaffId;
import com.heartbeat.myapp.util.SpringContextUtil;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class StaffEmail implements Serializable {

    private final String value;

    private final StaffService staffService;

    public StaffEmail(String value) {
        this.value = value;
        this.staffService = SpringContextUtil.getBean(StaffService.class);
    }

    public Boolean isExist() {
        StaffId staffId = staffService.getStaffIdBy(this);
        return staffId != null;
    }

    public Boolean isExist(StaffId excludeStaffId) {
        StaffId staffId = this.staffService.getStaffIdBy(this);
        return staffId != null && !staffId.equals(excludeStaffId);
    }
}
