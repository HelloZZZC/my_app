package com.heartbeat.myapp.domain.service;

import com.heartbeat.myapp.domain.model.Account;
import com.heartbeat.myapp.domain.model.Staff;

public interface AccountDomainService {

    void validateStaffAccount(Staff staff, Account account);
}
