package com.heartbeat.myapp.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.heartbeat.myapp.dao.dataobject.AccountDO;
import com.heartbeat.myapp.dao.mapper.AccountMapper;
import com.heartbeat.myapp.domain.model.Account;
import com.heartbeat.myapp.dp.identifier.AccountId;
import com.heartbeat.myapp.repository.AccountRepository;
import com.heartbeat.myapp.repository.converter.AccountConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountConverter accountConverter;

    @Override
    public Account get(AccountId accountId) {
        LambdaQueryWrapper<AccountDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AccountDO::getId, accountId.getValue());
        AccountDO accountDO = accountMapper.selectOne(queryWrapper);

        return accountConverter.toAccount(accountDO);
    }

    @Override
    public Account getByUsername(String username) {
        LambdaQueryWrapper<AccountDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AccountDO::getUsername, username);
        AccountDO accountDO = accountMapper.selectOne(queryWrapper);

        return accountConverter.toAccount(accountDO);
    }
}
