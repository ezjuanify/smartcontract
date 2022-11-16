package com.dotjuan89.smartcontract.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dotjuan89.smartcontract.entity.TransactionEntity;

/**
 * SolidityController
 */
@RestController
public class SolidityController {

    @RequestMapping(value = "/send", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<?> send() {
        TransactionEntity t = new TransactionEntity(0, "ok");
        return ResponseEntity.ok(t.getTransactionJson());
    }
    
}