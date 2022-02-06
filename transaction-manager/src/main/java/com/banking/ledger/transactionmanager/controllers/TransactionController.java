package com.banking.ledger.transactionmanager.controllers;

import com.banking.ledger.transactionmanager.beans.Transaction;
import com.banking.ledger.transactionmanager.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping(path = "/get/{transactionRef}")
    public ResponseEntity getCurrencyByCode(@PathVariable(value = "transactionRef") int transactionRef) {

        try {
            Transaction transaction = transactionService.getTransactionById(transactionRef);
            return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/getTransactionHistory/{transactionRef}")
    public ResponseEntity getTransactionHistory(@PathVariable(value = "transactionRef") int transactionRef) {
        try {
            List<Transaction> transactions = transactionService.getTransactionHistory(transactionRef);
            return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/getTransactionByUser/{userId}")
    public ResponseEntity getTransactionByUser(@PathVariable(value = "userId") int userId) {
        try {
            List<Transaction> transactions = transactionService.getTransactionsByUser(userId);
            return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/create")
    public ResponseEntity createTransaction(@RequestBody Transaction transaction,
                                         @RequestHeader("userName") String userName, @RequestHeader("token") String token) {
        Transaction transaction1 = transactionService.createTransaction(transaction, userName, token);
        if(transaction1 != null){
            return new ResponseEntity<Transaction>(transaction1, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping(path = "/update")
    public ResponseEntity updateCurrency(@RequestBody Transaction transaction,
                                      @RequestHeader("userName") String userName, @RequestHeader("token") String token) {
        Transaction transaction1 =transactionService.updateTransaction(transaction, userName, token);
        if(transaction1 != null){
            return new ResponseEntity<Transaction>(transaction1, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
