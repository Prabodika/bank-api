package com.monese.test.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "account_type")
public class AccountType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;

    @OneToMany(mappedBy = "accountType")
    private Set<Account> account;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Account> getAccount() {
        return account;
    }

    public void setAccount(Set<Account> account) {
        this.account = account;
    }
}
