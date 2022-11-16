package com.dotjuan89.smartcontract.entity;

import java.util.HashMap;
import java.util.Map;


public class TransactionEntity {
    private Integer id;
    private String code;
    private Double amount;

    public TransactionEntity() {}
    public TransactionEntity(Integer id, String code) {
        this.id = id;
        this.code = code;
    }
    public TransactionEntity(Integer id, String code, Double amount) {
        this.id = id;
        this.code = code;
        this.amount = amount;
    }

    public HashMap<String, Object> getTransactionJson() {
        HashMap<String, Object> t = new HashMap<String, Object>();
        t.put("id", id);
        t.put("code", code);
        if (amount != null) {
            t.put("amount", amount);
            return t;
        }
        return t;
    }
}