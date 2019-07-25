package com.monese.test.repository;

import com.monese.test.model.AccountTransaction;
import com.monese.test.util.TransactionTypeEnum;

import java.math.BigDecimal;
import java.util.List;

public interface AccountTransactionDao {

    BigDecimal getBalance(String accountNo, TransactionTypeEnum type);

    List<AccountTransaction> getStatement(String accountNo);

    void transferMoney(List<AccountTransaction> transactionData, String accountNo, BigDecimal amount);


}
