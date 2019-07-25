package com.monese.test.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class AccountMoneyTransferDto implements Serializable {

    @NotEmpty(message = "Account Number can not be null or empty")
    private String accountNumber;
    @NotNull(message = "Amount Number can not be null or empty")
    private BigDecimal amount;
    @NotEmpty(message = "Beneficiary Name can not be null or empty")
    private String beneficiaryAccountName;
    @NotEmpty(message = "Beneficiary Account Number can not be null or empty")
    private String beneficiaryAccountNumber;
    @NotEmpty(message = "Description  can not be null or empty")
    private String description;


    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getBeneficiaryAccountName() {
        return beneficiaryAccountName;
    }

    public void setBeneficiaryAccountName(String beneficiaryAccountName) {
        this.beneficiaryAccountName = beneficiaryAccountName;
    }

    public String getBeneficiaryAccountNumber() {
        return beneficiaryAccountNumber;
    }

    public void setBeneficiaryAccountNumber(String beneficiaryAccountNumber) {
        this.beneficiaryAccountNumber = beneficiaryAccountNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
