package com.monese.test.util;

import com.monese.test.exceptions.UserException;
import com.monese.test.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountValidatorUtil {

    public void validateAccount(Account account) {
        if (account == null) {
            throw new UserException("Invalid account number ");
        }

    }

    public void validateBeneficiaryAccount(Account account, String name) {

        if (account == null || !name.equalsIgnoreCase(account.getName())) {
            throw new UserException("Invalid Beneficiary account ");
        }


    }


}
