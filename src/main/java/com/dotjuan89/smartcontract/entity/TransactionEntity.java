package com.dotjuan89.smartcontract.entity;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;

import javax.persistence.*;

@MappedSuperclass
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    protected String code;
    protected Timestamp timestamp;

    @Column(name = "public_key")
    protected String key;
    protected String signature;

    public TransactionEntity() {}
    public TransactionEntity(String code) {
        this.code = code;
        this.timestamp = Timestamp.from(Instant.now());
    }

    public Integer getId() { return id; }
    public String getCode() { return code; }
    public Timestamp getTimestamp() { return timestamp; }
    public String getKey() { return key; }
    public String getSignature() { return signature; }

    public void setId(Integer id) { this.id = id; }
    public void setCode(String code) { this.code = code; }
    public void setTimestamp() { this.timestamp = Timestamp.from(Instant.now()); }
    public void setKey(String key) { this.key = key; }
    public void setSignature(String signature) { this.signature = signature; }

    public HashMap<String, Object> getJson() {
        HashMap<String, Object> m = new HashMap<String, Object>();
        m.put("id", id);
        m.put("code", code);
        return m;
    }
}