package com.dotjuan89.smartcontract.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dotjuan89.smartcontract.entity.BalanceEntity;
import com.dotjuan89.smartcontract.entity.SendEntity;
import com.dotjuan89.smartcontract.entity.TransactionEntity;
import com.dotjuan89.smartcontract.repository.BalanceRepository;
import com.dotjuan89.smartcontract.repository.SendRepository;
import com.dotjuan89.smartcontract.service.SignatureService;

/**
 * SolidityController
 */
@RestController
public class SolidityController {

    private static final String OKResponse = "OK";
    private static final String ERRResponse = "ERR";

    @Autowired
    BalanceRepository balanceRepository;

    @Autowired
    SendRepository sendRepository;


    @RequestMapping(value = "/send", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<?> send(@RequestParam String from, @RequestParam String to, @RequestParam Double amount) {
        System.out.printf("%s %s %.2f", from, to, amount);
        TransactionEntity t = new SendEntity(OKResponse, from, to, amount);

        sendRepository.save((SendEntity) t);

        return ResponseEntity.ok(t.getJson());
    }

    @RequestMapping(value = "/get-balance", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<?> getBalance(@RequestParam String account) {
        TransactionEntity t = new BalanceEntity(ERRResponse, account);

        balanceRepository.save((BalanceEntity) t);

        SignatureService.generateSignature(t);
        return ResponseEntity.ok(t.getJson());
    }
    
}