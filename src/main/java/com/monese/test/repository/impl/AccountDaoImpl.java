package com.monese.test.repository.impl;

import com.monese.test.model.Account;
import com.monese.test.repository.AccountDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
@Transactional
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Account getAccount(String accountNo) {

        Session session = this.sessionFactory.getCurrentSession();
        Account account = null;

        Query query = session.createQuery(" SELECT a FROM Account a WHERE a.accountNumber = ?1 ");
        query.setParameter(1, accountNo);
        if (query.getResultList().size() < 1) {

            return account;
        }
        return (Account) query.getResultList().get(0);

    }
}
