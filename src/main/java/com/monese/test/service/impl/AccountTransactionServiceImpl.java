package com.monese.test.service.impl;


import com.monese.test.dto.AccountMoneyTransferDto;
import com.monese.test.dto.AccountTransactionDto;
import com.monese.test.exceptions.UserException;
import com.monese.test.model.Account;
import com.monese.test.model.AccountTransaction;
import com.monese.test.model.TransactionType;
import com.monese.test.repository.AccountTransactionDao;
import com.monese.test.repository.TransactionDao;
import com.monese.test.service.AccountService;
import com.monese.test.service.AccountTransactionService;
import com.monese.test.util.AccountValidatorUtil;
import com.monese.test.util.TransactionTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountTransactionServiceImpl implements AccountTransactionService {


    @Autowired
    private AccountTransactionDao accountTransactionDao;

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountValidatorUtil accountValidatorUtil;


    @Override
    public BigDecimal getBalance(String accountNo) {

        Account account = accountService.getAccount(accountNo);
        accountValidatorUtil.validateAccount(account);

        BigDecimal depositValue = accountTransactionDao.getBalance(accountNo, TransactionTypeEnum.DEPOSIT);
        BigDecimal withdrawalValue = accountTransactionDao.getBalance(accountNo, TransactionTypeEnum.WITHDRAWAL);
        return depositValue.subtract(withdrawalValue);

    }

    @Override
    public List<AccountTransactionDto> getStatement(String accountNo) {

        Account account = accountService.getAccount(accountNo);
        accountValidatorUtil.validateAccount(account);

        List<AccountTransaction> transactionDtoList = accountTransactionDao.getStatement(accountNo);
        List<AccountTransactionDto> accountTransactionDtoList = new ArrayList<>();

        for (AccountTransaction transaction : transactionDtoList) {

            AccountTransactionDto accountTransactionDto = new AccountTransactionDto();
            accountTransactionDto.setAmount(transaction.getAmount());
            accountTransactionDto.setDescription(transaction.getDescription());
            accountTransactionDto.setDate(transaction.getDate());
            accountTransactionDto.setTransactionType(transaction.getTransactionType().getType());

            accountTransactionDtoList.add(accountTransactionDto);
        }


        return accountTransactionDtoList;
    }


    @Override
    @Transactional
    public AccountMoneyTransferDto transferMoney(AccountMoneyTransferDto transactionData) {

        //get sender account
        Account account = accountService.getAccount(transactionData.getAccountNumber());
        accountValidatorUtil.validateAccount(account);

        //get Beneficiary account
        Account beneficiaryAccount = accountService.getAccount(transactionData.getBeneficiaryAccountNumber());
        accountValidatorUtil.validateBeneficiaryAccount(beneficiaryAccount, beneficiaryAccount.getName());


        BigDecimal balance = getBalance(transactionData.getAccountNumber());
        BigDecimal futureBalance = balance.subtract(transactionData.getAmount());
        validateBalance(futureBalance);

        AccountTransaction depositTransaction = createTransactionData(transactionData, TransactionTypeEnum.DEPOSIT, beneficiaryAccount);
        AccountTransaction withdrawalTransaction = createTransactionData(transactionData, TransactionTypeEnum.WITHDRAWAL, account);

        List<AccountTransaction> transactionList = new ArrayList<>();
        transactionList.add(depositTransaction);
        transactionList.add(withdrawalTransaction);
        accountTransactionDao.transferMoney(transactionList, transactionData.getAccountNumber(), transactionData.getAmount());

        return transactionData;
    }


    private AccountTransaction createTransactionData(AccountMoneyTransferDto transactionData, TransactionTypeEnum type, Account account) {

        TransactionType transactionType = transactionDao.getTransaction(type);
        AccountTransaction transaction = new AccountTransaction();
        transaction.setAccount(account);
        transaction.setDescription(transactionData.getDescription());
        transaction.setAmount(transactionData.getAmount());
        transaction.setDate(LocalDate.now());
        transaction.setTransactionType(transactionType);
        return transaction;
    }


    private void validateBalance(BigDecimal balance) {

        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new UserException("Insufficient Balance");
        }


    }


}
