package com.heartbeat.myapp.biz.auth.impl;

import com.heartbeat.myapp.biz.auth.AuthService;
import com.heartbeat.myapp.constant.AuthConstant;
import com.heartbeat.myapp.domain.model.Account;
import com.heartbeat.myapp.domain.model.Staff;
import com.heartbeat.myapp.dp.Password;
import com.heartbeat.myapp.dp.identifier.StaffId;
import com.heartbeat.myapp.repository.AccountRepository;
import com.heartbeat.myapp.repository.StaffRepository;
import com.heartbeat.myapp.util.JwtUtil;
import com.heartbeat.myapp.util.RedissonCacheUtil;
import com.heartbeat.myapp.web.param.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private RedissonCacheUtil redissonCacheUtil;

    @Override
    public String login(LoginParam param) {
        Account account = accountRepository.getByUsername(param.getUsername());
        if (ObjectUtils.isEmpty(account)) {
            throw new RuntimeException();
        }
        Boolean isRight = account.verifyPassword(new Password(param.getPassword()));
        if (!isRight) {
            throw new RuntimeException();
        }
        Staff staff = staffRepository.get(new StaffId(account.getStaffId()));
        if (!staff.isEmployment()) {
            throw new RuntimeException();
        }
        String token = JwtUtil.generate(staff.getId().getValue());
        String CacheKey = String.format(AuthConstant.STAFF_TOKEN_FORMAT, token);
        redissonCacheUtil.set(CacheKey, staff.getId().getValue(), JwtUtil.getTtl());

        return token;
    }
}
