package com.dotjuan89.smartcontract.service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

import org.springframework.stereotype.Service;

import com.dotjuan89.smartcontract.entity.BalanceEntity;
import com.dotjuan89.smartcontract.entity.SendEntity;
import com.dotjuan89.smartcontract.entity.TransactionEntity;

@Service
public class SignatureService {
    private PublicKey pubKey;
    private PrivateKey priKey;

    private String getParamVal(TransactionEntity t) {
        if (t instanceof SendEntity) { 
            SendEntity e = (SendEntity) t;
            return String.format("%s%s%s%s%s%.4f", "from", e.getFrom(), "to", e.getTo(), "amount", e.getAmount());
        }
        if (t instanceof BalanceEntity) {
            BalanceEntity e = (BalanceEntity) t;
            return String.format("%s%s", "account", e.getAccount());
        }
        return "";
    }

    private void generateKey() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            KeyPair keyPair = keyGen.generateKeyPair();
            pubKey = keyPair.getPublic();
            priKey = keyPair.getPrivate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String signMessage(String message) {
        try {
            Signature privateSignature = Signature.getInstance("SHA256withRSA");
            privateSignature.initSign(priKey);
            privateSignature.update(message.getBytes("UTF-8"));

            byte[] signature = privateSignature.sign();

            return Base64.getEncoder().encodeToString(signature);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    public void generateSignature(TransactionEntity t) {
        generateKey();
        String pubKeyString = Base64.getEncoder().encodeToString(pubKey.getEncoded());
        String s = String.format("%s%s%s%s", t.getId(), t.getTimestamp(), getParamVal(t), pubKeyString);
        t.setKey(pubKeyString);

        String signatue = signMessage(s);
        t.setSignature(signatue);
    }
}
