package com.dotjuan89.smartcontract.entity;

import java.util.HashMap;

import javax.persistence.*;

@Entity
@Table(name = "TRANSACTION_BALANCE")
public class BalanceEntity extends TransactionEntity {
    private String account;

    public BalanceEntity() { super(); }
    public BalanceEntity(String code, String account) {
        super(code);
        this.account = account;
    }

    public String getAccount() { return account; }

    public void setAccount(String account) { this.account = account; }

    @Override
    public HashMap<String, Object> getJson() {
        HashMap<String, Object> m = super.getJson();
        m.put("account", account);
        return m;
    }
}
