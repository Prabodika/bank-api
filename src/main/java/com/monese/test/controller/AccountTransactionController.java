package com.monese.test.controller;

import com.monese.test.dto.AccountMoneyTransferDto;
import com.monese.test.dto.AccountTransactionDto;
import com.monese.test.service.AccountTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/v1/bank-api/account")
public class AccountTransactionController {

    @Autowired
    private AccountTransactionService accountTransactionService;


    @PostMapping("/transaction")
    public AccountMoneyTransferDto depositMoney(@RequestBody @Valid @NotNull(message = "Request body cannot be empty") AccountMoneyTransferDto accountMoneyTransferDto) {

        return accountTransactionService.transferMoney(accountMoneyTransferDto);
    }

    @GetMapping("/{accNo}/statement")
    public List<AccountTransactionDto> getAccountStatement(@PathVariable("accNo") String accountNo) {

        return accountTransactionService.getStatement(accountNo);
    }


    @GetMapping("/{accNo}/balance")
    public BigDecimal getAccountBalance(@PathVariable("accNo") String accountNo) {

        return accountTransactionService.getBalance(accountNo);
    }

}
