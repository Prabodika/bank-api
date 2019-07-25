package com.monese.test.util;

public enum TransactionTypeEnum {

    WITHDRAWAL("withdrawal"),
    DEPOSIT("deposit");

    private String transactionType;

    TransactionTypeEnum(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionType() {
        return transactionType;
    }
}
