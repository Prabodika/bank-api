package com.monese.test.controller;

import com.monese.test.dto.AccountMoneyTransferDto;
import com.monese.test.dto.AccountTransactionDto;
import com.monese.test.service.AccountTransactionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountTransactionControllerTest {

    @Autowired
    private AccountTransactionController accountTransactionController;

    @MockBean
    private AccountTransactionService accountTransactionService;
    ;

    @Test
    public void shouldReturnAccountBalance() {

        when(accountTransactionService.getBalance("123")).thenReturn(BigDecimal.valueOf(10.00));

        BigDecimal balance = accountTransactionController.getAccountBalance("123");
        Assert.assertEquals(balance, BigDecimal.valueOf(10.00));
    }


    @Test
    public void shouldReturnAccountStatement() {


        List<AccountTransactionDto> transactionDtoList = new ArrayList<>();
        AccountTransactionDto accountTransactionDto = new AccountTransactionDto();
        accountTransactionDto.setDate(LocalDate.now());
        accountTransactionDto.setAmount(BigDecimal.TEN);
        accountTransactionDto.setDescription("test Value");
        accountTransactionDto.setDescription("test data");
        transactionDtoList.add(accountTransactionDto);

        when(accountTransactionService.getStatement("123")).thenReturn(transactionDtoList);
        List<AccountTransactionDto> transactionList = accountTransactionController.getAccountStatement("123");
        Assert.assertNotNull(transactionList);
        Assert.assertEquals(transactionList.get(0).getAmount(), BigDecimal.TEN);


    }

    @Test
    public void shouldDepositMoney() {


        AccountMoneyTransferDto transferDto = new AccountMoneyTransferDto();
        transferDto.setAccountNumber("123");
        transferDto.setAmount(BigDecimal.TEN);
        transferDto.setBeneficiaryAccountName("test");
        transferDto.setBeneficiaryAccountName("321");
        transferDto.setDescription("test");
        when(accountTransactionService.transferMoney(transferDto)).thenReturn(transferDto);

        AccountMoneyTransferDto moneyTransferDto = accountTransactionController.depositMoney(transferDto);
        Assert.assertNotNull(moneyTransferDto);
        Assert.assertEquals(moneyTransferDto.getAmount(), BigDecimal.TEN);

    }

}
