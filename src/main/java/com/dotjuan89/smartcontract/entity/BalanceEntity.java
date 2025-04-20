package com.dotjuan89.smartcontract.entity;

import java.util.HashMap;

import javax.persistence.*;

@Entity
@Table(name = "TRANSACTION_BALANCE")
public class BalanceEntity extends TransactionEntity {
    private String account;
    private Double amount;

    public BalanceEntity() { super(); }
    public BalanceEntity(String code, String account) {
        super(code);
        this.account = account;
    }
    public BalanceEntity(String code, String account, Double amount) {
        super(code);
        this.account = account;
        this.amount = amount;
    }

    public String getAccount() { return account; }
    public Double getAmount() { return amount; }

    public void setAccount(String account) { this.account = account; }
    public void setAmount(Double amount) { this.amount = amount; }

    @Override
    public HashMap<String, Object> getJson() {
        HashMap<String, Object> m = super.getJson();
        m.put("account", account);
        if (amount != null) m.put("amount", amount);
        return m;
    }
}
