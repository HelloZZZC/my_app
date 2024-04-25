package com.heartbeat.myapp.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.heartbeat.myapp.dao.dataobject.AccountDO;
import com.heartbeat.myapp.dao.mapper.AccountMapper;
import com.heartbeat.myapp.domain.model.Account;
import com.heartbeat.myapp.dp.Username;
import com.heartbeat.myapp.dp.identifier.AccountId;
import com.heartbeat.myapp.repository.AccountRepository;
import com.heartbeat.myapp.repository.converter.AccountConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    private AccountMapper mapper;

    @Autowired
    private AccountConverter converter;

    @Override
    public Account get(AccountId accountId) {
        LambdaQueryWrapper<AccountDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AccountDO::getId, accountId.getValue());
        AccountDO accountDO = mapper.selectOne(queryWrapper);

        return ObjectUtils.isEmpty(accountDO) ? null : converter.toAccount(accountDO);
    }

    @Override
    public Account getByUsername(Username username) {
        LambdaQueryWrapper<AccountDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AccountDO::getUsername, username.getValue());
        AccountDO accountDO = mapper.selectOne(queryWrapper);

        return ObjectUtils.isEmpty(accountDO) ? null : converter.toAccount(accountDO);
    }

    @Override
    public AccountId getAccountIdBy(Username username) {
        LambdaQueryWrapper<AccountDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AccountDO::getUsername, username.getValue());
        AccountDO accountDO = mapper.selectOne(queryWrapper);

        return ObjectUtils.isEmpty(accountDO) ? null : new AccountId(accountDO.getId());
    }

    @Override
    public AccountId insert(AccountDO accountDO) {
        mapper.insert(accountDO);
        return new AccountId(accountDO.getId());
    }
}
