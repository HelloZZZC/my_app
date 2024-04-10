package com.heartbeat.myapp.biz.impl;

import com.heartbeat.myapp.biz.RoleService;
import com.heartbeat.myapp.dp.identifier.RoleId;
import com.heartbeat.myapp.dto.RoleDTO;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Override
    public RoleDTO getRole(RoleId roleId) {

        return new RoleDTO();
    }


}
