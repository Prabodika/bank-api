package com.monese.test.service.impl;

import com.monese.test.model.Account;
import com.monese.test.repository.AccountDao;
import com.monese.test.service.AccountService;
import com.monese.test.util.AccountValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {


    @Autowired
    private AccountDao accountDao;

    @Autowired
    private AccountValidatorUtil accountValidatorUtil;


    @Override
    public Account getAccount(String accountNo) {

        Account account = accountDao.getAccount(accountNo);
        accountValidatorUtil.validateAccount(account);
        return account;

    }


}
