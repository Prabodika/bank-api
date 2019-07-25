package com.monese.test.repository.impl;

import com.monese.test.model.AccountTransaction;
import com.monese.test.repository.AccountTransactionDao;
import com.monese.test.util.TransactionTypeEnum;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class AccountTransactionDaoImpl implements AccountTransactionDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public BigDecimal getBalance(String accountNo, TransactionTypeEnum type) {

        Session session = this.sessionFactory.getCurrentSession();

        Query query = session.createQuery(" SELECT SUM(t.amount) FROM AccountTransaction t INNER JOIN t.account a INNER JOIN t.transactionType tt WHERE a.accountNumber = ?1 AND tt.type= ?2 group by t.transactionType");
        query.setParameter(1, accountNo);
        query.setParameter(2, type.getTransactionType());
        if (query.getResultList().size() < 1) {
            return BigDecimal.ZERO;
        }
        return (BigDecimal) query.getResultList().get(0);


    }

    @Override
    public List<AccountTransaction> getStatement(String accountNo) {

        List<AccountTransaction> transactionList = new ArrayList<>();
        Session session = this.sessionFactory.getCurrentSession();

        Query query = session.createQuery("SELECT t FROM AccountTransaction t INNER JOIN t.account a  WHERE a.accountNumber=?1 ");
        query.setParameter(1, accountNo);

        if (query.getResultList().size() < 1) {
            return transactionList;
        }

        return query.getResultList();


    }

    @Override
    public void transferMoney(List<AccountTransaction> transactions, String accountNo, BigDecimal amount) {

        Session session = this.sessionFactory.getCurrentSession();

        for (AccountTransaction transaction : transactions) {
            session.persist(transaction);
        }

    }


}
