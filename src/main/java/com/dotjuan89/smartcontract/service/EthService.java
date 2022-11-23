package com.dotjuan89.smartcontract.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.Function;
import org.web3j.crypto.RawTransaction;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;

@Service
public class EthService {
    private static Web3j web3a;

    @EventListener(ApplicationReadyEvent.class)
    public void connect() {
        web3a = Web3j.build(new HttpService());
    }

    public BigDecimal getBalance(String id) throws Exception {
        EthGasPrice gasPrice = web3a.ethGasPrice().send();
        System.out.printf("Default Gas Price: %s\n", gasPrice.getGasPrice());

        EthGetBalance ethGetBalance = web3a.ethGetBalance(id, DefaultBlockParameterName.LATEST).sendAsync().get();

        System.out.printf("Balance: of Account '%s' %s\n", id, ethGetBalance.getBalance());

        BigDecimal ethBalance = Convert.fromWei(web3a.ethGetBalance(id,DefaultBlockParameterName.LATEST).send().getBalance().toString(), Convert.Unit.ETHER);
        System.out.printf("Balance in Ether format: %s", ethBalance);
        return ethBalance;
    }

    public String sendTransaction(String from, String to, Double amount) throws Exception {
        EthGetTransactionCount ethGetTransactionCount = web3a.ethGetTransactionCount(from,DefaultBlockParameterName.LATEST).send();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
        BigInteger gasLimit = BigInteger.valueOf(21000);
        BigInteger gasPrice = Convert.toWei("1", Unit.GWEI).toBigInteger();
        BigInteger value = Convert.toWei(amount.toString(), Convert.Unit.ETHER).toBigInteger();


        // Transaction transaction = Transaction.createEtherTransaction(from, nonce, gasPrice, gasLimit, to, value);
        Transaction transaction = Transaction.createEtherTransaction(from, nonce, gasPrice, gasLimit, to, value);
        
        EthSendTransaction transactionResponse = web3a.ethSendTransaction(transaction).sendAsync().get();
        return transactionResponse.getTransactionHash();
    }
}
