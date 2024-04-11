package com.heartbeat.myapp.components.shiro;

import com.heartbeat.myapp.biz.StaffService;
import com.heartbeat.myapp.dp.identifier.StaffId;
import com.heartbeat.myapp.dto.StaffDTO;
import com.heartbeat.myapp.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private StaffService staffService;

    /**
     * 授权
     *
     * @param principalCollection PrincipalCollection
     * @return AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Integer staffId = (Integer) principalCollection.getPrimaryPrincipal();
        StaffDTO staffDTO = staffService.getStaff(new StaffId(staffId));

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 设置角色
        Set<String> roles = new HashSet<>();
        roles.add(staffDTO.getRoleDTO().getCode());
        info.setRoles(roles);
        // 设置权限
        Set<String> permissions = new HashSet<>();
        staffDTO.getRoleDTO().getPermissions().forEach(permissionDTO -> {
            permissions.add(permissionDTO.getCode());
        });
        info.setStringPermissions(permissions);

        return info;
    }

    /**
     * 授权
     * @param authenticationToken AuthenticationToken
     * @return AuthenticationInfo
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        String jwtToken = (String) authenticationToken.getCredentials();
        if (!JwtUtil.verify(jwtToken)) {
            throw new RuntimeException();
        }
        Integer staffId = JwtUtil.getStaffId(jwtToken);

        return new SimpleAuthenticationInfo(staffId, jwtToken, getName());
    }
}
