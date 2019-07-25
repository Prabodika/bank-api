package com.monese.test.repository;

import com.monese.test.model.TransactionType;
import com.monese.test.util.TransactionTypeEnum;

public interface TransactionDao {

    TransactionType getTransaction(TransactionTypeEnum transactionType);

}
