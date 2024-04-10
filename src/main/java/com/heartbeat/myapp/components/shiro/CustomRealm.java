package com.heartbeat.myapp.components.shiro;

import com.heartbeat.myapp.repository.StaffRepository;
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
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomRealm extends AuthorizingRealm {

    private StaffRepository staffRepository;

    /**
     * 授权
     *
     * @param principalCollection PrincipalCollection
     * @return AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Integer staffId = (Integer) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

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
