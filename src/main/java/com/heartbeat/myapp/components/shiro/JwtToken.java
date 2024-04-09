package com.heartbeat.myapp.components.shiro;

import com.heartbeat.myapp.util.JwtUtil;
import org.apache.shiro.authc.AuthenticationToken;

public class JwtToken implements AuthenticationToken {

    private final String token;

    private final Integer staffId;

    public JwtToken(String token) {
        this.token = token;
        this.staffId = JwtUtil.getStaffId(token);
    }

    @Override
    public Object getPrincipal() {
        return this.staffId;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }
}
