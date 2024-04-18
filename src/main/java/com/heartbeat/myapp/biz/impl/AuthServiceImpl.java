package com.heartbeat.myapp.biz.impl;

import com.heartbeat.myapp.biz.AuthService;
import com.heartbeat.myapp.domain.model.Account;
import com.heartbeat.myapp.domain.model.Staff;
import com.heartbeat.myapp.dp.JwtTokenCache;
import com.heartbeat.myapp.dp.Password;
import com.heartbeat.myapp.dp.identifier.StaffId;
import com.heartbeat.myapp.exception.BizException;
import com.heartbeat.myapp.exception.errorcode.AuthErrorCode;
import com.heartbeat.myapp.exception.errorcode.StaffErrorCode;
import com.heartbeat.myapp.repository.AccountRepository;
import com.heartbeat.myapp.repository.StaffRepository;
import com.heartbeat.myapp.util.JwtUtil;
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

    @Override
    public String login(LoginParam param) {
        Account account = accountRepository.getByUsername(param.getUsername());
        if (ObjectUtils.isEmpty(account)) {
            throw new BizException(AuthErrorCode.ACCOUNT_NOT_FOUND);
        }
        Boolean isRight = account.verifyPassword(new Password(param.getPassword()));
        if (!isRight) {
            throw new BizException(AuthErrorCode.PASSWORD_CHECK_FAILURE);
        }
        Staff staff = staffRepository.get(new StaffId(account.getStaffId()));
        if (!staff.isEmployment()) {
            throw new BizException(StaffErrorCode.STAFF_NOT_EMPLOYMENT, String.format("系统职工[id:%d]状态非在职",
                    staff.getId().getValue()));
        }
        String token = JwtUtil.generate(staff.getId().getValue());
        new JwtTokenCache(token).save();

        return token;
    }
}
