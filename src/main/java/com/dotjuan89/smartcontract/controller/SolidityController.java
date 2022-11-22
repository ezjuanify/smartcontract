package com.dotjuan89.smartcontract.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dotjuan89.smartcontract.entity.TransactionEntity;

/**
 * SolidityController
 */
@RestController
public class SolidityController {

    @RequestMapping(value = "/send", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<?> send(@RequestParam String from, @RequestParam String to, @RequestParam Double amount) {
        System.out.printf("%s %s %.2f", from, to, amount);
        TransactionEntity t = new TransactionEntity(0, "ok");
        return ResponseEntity.ok(t.getTransactionJson());
    }

    @RequestMapping(value = "/get-balance", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<?> getBalance(@RequestParam String account) {
        TransactionEntity t = new TransactionEntity(0, "ok", 100.00);
        return ResponseEntity.ok(t.getTransactionJson());
    }
    
}