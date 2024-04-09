package com.heartbeat.myapp.biz.impl;

import com.heartbeat.myapp.biz.StaffService;
import com.heartbeat.myapp.domain.model.Staff;
import com.heartbeat.myapp.dp.identifier.StaffId;
import com.heartbeat.myapp.dto.StaffDTO;
import com.heartbeat.myapp.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public StaffDTO getStaff(StaffId id) {
        Staff staff = staffRepository.get(id);

        return new StaffDTO();
    }
}
