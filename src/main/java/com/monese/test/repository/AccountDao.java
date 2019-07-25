package com.monese.test.repository;

import com.monese.test.model.Account;

public interface AccountDao {

    Account getAccount(String accountNo);
}
