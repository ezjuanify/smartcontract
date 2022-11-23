package com.dotjuan89.smartcontract.entity;

import java.util.HashMap;

import javax.persistence.*;

@Entity
@Table(name = "TRANSACTION_SEND")
public class SendEntity extends TransactionEntity {
    private String from;
    private String to;
    private Double amount;

    public SendEntity() { super(); }
    public SendEntity(String code, String from, String to, Double amount) {
        super(code);
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public String getFrom() { return from; }
    public String getTo() { return to; }
    public Double getAmount() { return amount; }

    public void setFrom(String from) { this.from = from; }
    public void setTo(String to) { this.to = to; }
    public void setAmount(Double amount) { this.amount = amount; }

    @Override
    public HashMap<String, Object> getJson() {
        HashMap<String, Object> m = super.getJson();
        m.put("from", from);
        m.put("to", to);
        m.put("amount", amount);
        return m;
    }
}
