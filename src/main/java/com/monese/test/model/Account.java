package com.monese.test.model;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "account_number")
    @UniqueElements
    private String accountNumber;

    @Column
    private String name;

    @Lazy
    @ManyToOne
    @JoinColumn(name = "account_type", nullable = false)
    private AccountType accountType;

    @OneToMany(mappedBy = "account")
    private Set<AccountTransaction> transaction;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Set<AccountTransaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(Set<AccountTransaction> transaction) {
        this.transaction = transaction;
    }
}
