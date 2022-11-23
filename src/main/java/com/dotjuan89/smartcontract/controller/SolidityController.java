package com.dotjuan89.smartcontract.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.dotjuan89.smartcontract.service.EthService;
import com.dotjuan89.smartcontract.service.SignatureService;

/**
 * SolidityController
 */
@RestController
public class SolidityController {
    private static final String OKResponse = "OK";
    private static final String ERRResponse = "ERR";

    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private SendRepository sendRepository;

    @Autowired
    private SignatureService signatureService;

    @Autowired
    private EthService ethService;

    @RequestMapping(value = "/send", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<?> send(@RequestParam String from, @RequestParam String to, @RequestParam Double amount) {
        try {
            ethService.sendTransaction(from, to, amount);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new SendEntity(ERRResponse, from, to, amount).getJson());
        }

        TransactionEntity t = new SendEntity(OKResponse, from, to, amount);

        sendRepository.save((SendEntity) t);

        return ResponseEntity.ok(t.getJson());
    }

    @RequestMapping(value = "/get-balance", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<?> getBalance(@RequestParam String account) {
        Double ethBalance = 0.0;
        try {
            ethBalance = ethService.getBalance(account).doubleValue();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BalanceEntity(ERRResponse, account).getJson());
        }

        TransactionEntity t = new BalanceEntity(OKResponse, account, ethBalance);
        balanceRepository.save((BalanceEntity) t);

        signatureService.generateSignature(t);
        return ResponseEntity.ok(t.getJson());
    }
    
}