package com.heartbeat.myapp.components.shiro;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.heartbeat.myapp.constant.CommonConstant;
import com.heartbeat.myapp.exception.BizException;
import com.heartbeat.myapp.exception.errorcode.AuthErrorCode;
import com.heartbeat.myapp.web.model.ErrBodyVO;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {

    /**
     * 判断用户是否想要登入。
     * 检测header里面是否包含Authorization字段即可
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader(CommonConstant.AUTHORIZATION_HEADER);
        return authorization != null;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(CommonConstant.AUTHORIZATION_HEADER);

        JwtToken jwtToken = new JwtToken(token);
        getSubject(request, response).login(jwtToken);

        return true;
    }

    /**
     * 登录认证
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (!isLoginAttempt(request, response)) {
            return true;
        }
        try {
            return executeLogin(request, response);
        } catch (AuthenticationException e) {
            return false;
        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        ErrBodyVO errBodyVO = new ErrBodyVO();
        errBodyVO.setErrCode(AuthErrorCode.AUTH_VERIFY_FAILURE.getCode());
        errBodyVO.setErrMsg(AuthErrorCode.AUTH_VERIFY_FAILURE.getMessage());

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().print(JSON.toJSONString(errBodyVO, SerializerFeature.WriteMapNullValue));
            response.getWriter().flush();
        } catch (Exception e) {
            log.error("Response Write Failure: Msg:{}", e.getMessage());
        }

        this.sendChallenge(request, response);

        return false;
    }

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers",
                httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个OPTIONS请求，这里我们给OPTIONS请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}
