package com.monese.test.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "transaction_type")
public class TransactionType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String type;
    @OneToMany(mappedBy = "transactionType")
    private Set<AccountTransaction> transaction;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<AccountTransaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(Set<AccountTransaction> transaction) {
        this.transaction = transaction;
    }
}
