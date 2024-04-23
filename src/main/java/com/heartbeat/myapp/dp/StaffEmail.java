package com.heartbeat.myapp.dp;

import com.heartbeat.myapp.dp.identifier.StaffId;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class StaffEmail implements Serializable {

    private final String value;

    public StaffEmail(String value) {
        this.value = value;
    }

    public Boolean isExist() {
        return Boolean.FALSE;
    }

    public Boolean isExist(StaffId excludeStaffId) {
        return Boolean.FALSE;
    }
}
