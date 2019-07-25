package com.monese.test.repository.impl;

import com.monese.test.model.TransactionType;
import com.monese.test.repository.TransactionDao;
import com.monese.test.util.TransactionTypeEnum;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
@Transactional
public class TransactionDaoImpl implements TransactionDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public TransactionType getTransaction(TransactionTypeEnum type) {

        Session session = this.sessionFactory.getCurrentSession();
        TransactionType transactionType = null;

        Query query = session.createQuery(" SELECT t FROM TransactionType t WHERE t.type = ?1 ");
        query.setParameter(1, type.getTransactionType());
        if (query.getResultList().size() < 1) {

            return transactionType;
        }
        return (TransactionType) query.getResultList().get(0);


    }
}
