package com.heartbeat.myapp.biz;

import com.heartbeat.myapp.web.param.LoginParam;

public interface AuthService {

    /**
     * 后台员工登录
     * @param param 登录参数
     * @return 登录成功返回token
     */
    String login(LoginParam param);
}
