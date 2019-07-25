package com.monese.test.service;

import com.monese.test.dto.AccountMoneyTransferDto;
import com.monese.test.dto.AccountTransactionDto;

import java.math.BigDecimal;
import java.util.List;

public interface AccountTransactionService {

    BigDecimal getBalance(String accountNo);

    List<AccountTransactionDto> getStatement(String accountNo);

    AccountMoneyTransferDto transferMoney(AccountMoneyTransferDto transactionData);
}
