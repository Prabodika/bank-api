package com.monese.test.service;

import com.monese.test.dto.AccountMoneyTransferDto;
import com.monese.test.dto.AccountTransactionDto;
import com.monese.test.model.Account;
import com.monese.test.model.AccountTransaction;
import com.monese.test.model.TransactionType;
import com.monese.test.repository.AccountTransactionDao;
import com.monese.test.util.AccountValidatorUtil;
import com.monese.test.util.TransactionTypeEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountTransactionServiceTest {

    @MockBean
    private AccountService accountService;

    @Autowired
    private AccountTransactionService accountTransactionService;

    @MockBean
    private AccountTransactionDao accountTransactionDao;

    @MockBean
    private AccountValidatorUtil accountValidatorUtil;


    @Test
    public void shouldReturnAccountBalance() {


        Account account = new Account();
        account.setAccountNumber("123");
        account.setName("test");

        when(accountService.getAccount("123")).thenReturn(account);
        when(accountTransactionDao.getBalance("123", TransactionTypeEnum.DEPOSIT)).thenReturn(BigDecimal.valueOf(500.00));
        when(accountTransactionDao.getBalance("123", TransactionTypeEnum.WITHDRAWAL)).thenReturn(BigDecimal.valueOf(400.00));

        doNothing().when(accountValidatorUtil).validateAccount(account);
        BigDecimal balance = accountTransactionService.getBalance("123");
        Assert.assertEquals(balance, BigDecimal.valueOf(100.00));
    }


    @Test
    public void shouldGetStatement() {


        Account account = new Account();
        account.setAccountNumber("123");
        account.setName("test");

        List<AccountTransaction> transactionDtoList = new ArrayList<>();
        TransactionType type = new TransactionType();
        type.setType(TransactionTypeEnum.DEPOSIT.getTransactionType());

        AccountTransaction accountTransaction = new AccountTransaction();
        accountTransaction.setAccount(account);
        accountTransaction.setAmount(BigDecimal.TEN);
        accountTransaction.setDate(LocalDate.now());
        accountTransaction.setDescription("test");
        accountTransaction.setTransactionType(type);
        transactionDtoList.add(accountTransaction);

        when(accountService.getAccount("123")).thenReturn(account);
        when(accountTransactionDao.getStatement("123")).thenReturn(transactionDtoList);
        doNothing().when(accountValidatorUtil).validateAccount(account);
        List<AccountTransactionDto> list = accountTransactionService.getStatement("123");
        Assert.assertNotNull(list);
        Assert.assertEquals(list.get(0).getAmount(), BigDecimal.TEN);

    }


    @Test
    public void shouldTransferMoney() {

        //AccountMoneyTransferDto transferMoney(AccountMoneyTransferDto transactionData)

        AccountMoneyTransferDto transactionData=new  AccountMoneyTransferDto();
        transactionData.setBeneficiaryAccountName("test");
        transactionData.setDescription("test");
        transactionData.setAmount(BigDecimal.TEN);
        transactionData.setAccountNumber("123");
        transactionData.setBeneficiaryAccountNumber("321");

        Account account = new Account();
        account.setAccountNumber("123");
        account.setName("test12");
        when(accountService.getAccount("123")).thenReturn(account);


        Account beneficiaryAccount = new Account();
        account.setAccountNumber("321");
        account.setName("test");
        when(accountService.getAccount("321")).thenReturn(beneficiaryAccount);
        doNothing().when(accountValidatorUtil).validateAccount(account);
        doNothing().when(accountValidatorUtil).validateBeneficiaryAccount(beneficiaryAccount,"test");
        when(accountTransactionDao.getBalance("123", TransactionTypeEnum.DEPOSIT)).thenReturn(BigDecimal.valueOf(500.00));
        when(accountTransactionDao.getBalance("123", TransactionTypeEnum.WITHDRAWAL)).thenReturn(BigDecimal.valueOf(400.00));

        doNothing().when(accountTransactionDao).transferMoney(ArgumentMatchers.anyListOf(AccountTransaction.class),ArgumentMatchers.anyString(),ArgumentMatchers.any());

        AccountMoneyTransferDto transactionDataAfter = accountTransactionService.transferMoney(transactionData);
        Assert.assertNotNull(transactionDataAfter);
    }



}
