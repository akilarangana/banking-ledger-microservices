package com.banking.ledger.accountmanager.controllers;

import com.banking.ledger.accountmanager.beans.Account;
import com.banking.ledger.accountmanager.beans.AccountBalance;
import com.banking.ledger.accountmanager.services.AccountBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountBalance")
public class AccountBalanceController {

    @Autowired
    AccountBalanceService accountBalanceService;

    @GetMapping(path = "/getBalance/{accountNumber}")
    public ResponseEntity getAccountBalanceByAccountNumber(@PathVariable(value = "accountNumber") String accountNumber) {
        try {
            AccountBalance accBalance = accountBalanceService.getAccBalanceByAccNumber(accountNumber);
            return new ResponseEntity<AccountBalance>(accBalance, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/create")
    public AccountBalance createAccBalance(@RequestBody AccountBalance accountBalance) {
        return accountBalanceService.createOrUpdateAccBalance(accountBalance);
    }

    @PutMapping(path = "/update")
    public AccountBalance updateCurrency(@RequestBody AccountBalance accountBalance) {
        return accountBalanceService.createOrUpdateAccBalance(accountBalance);
    }

    @GetMapping(path = "/getHistory/{accountNumber}")
    public ResponseEntity viewBalanceHistory(@PathVariable(value = "accountNumber") String accountNumber) {
        try {
            List<AccountBalance> accountBalances = accountBalanceService.viewAccountBalanceHistoryByAccNumber(accountNumber);
            return new ResponseEntity<List<AccountBalance>>(accountBalances, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
