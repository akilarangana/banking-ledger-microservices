package com.banking.ledger.accountmanager.controllers;

import com.banking.ledger.accountmanager.beans.Account;
import com.banking.ledger.accountmanager.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    /**
     * get the account number as the path variable and return ResponseEntity with an account object
     * @param accountNumber
     * @return ResponseEntity
     */
    @GetMapping(path = "/get/{accountNumber}")
    public ResponseEntity getAccountByAccountNumber(@PathVariable(value = "accountNumber") String accountNumber) {
        try {
            Account account = accountService.getAccountById(accountNumber);
            return new ResponseEntity<Account>(account, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * get the account object and insert account obj data into database and return account object with account id &
     * account number updated.
     * @param account
     * @return createdAccount with full account details
     */
    @PostMapping(path = "/create")
    public Account createCurrency(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    /**
     *  user need to send the entire account object to update. Is some fields are missing from the origin object, these
     *  field will be missed in the updated object
     * @param account
     * @return updated account
     */
    @PutMapping(path = "/update")
    public Account updateCurrency(@RequestBody Account account) {
        return accountService.updateAccount(account);
    }

    /**
     * user can update the account. This endpoint can be used to view the updated history
     * @param accountNumber
     * @return ResponseEntity with account history list
     */
    @GetMapping(path = "/getHistory/{accountNumber}")
    public ResponseEntity viewAccountHistory(@PathVariable(value = "accountNumber") String accountNumber) {
        try {
            List<Account> accountHistory = accountService.getAccountHistory(accountNumber);
            return new ResponseEntity<List<Account>>(accountHistory, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * One user can have multiple account. This endpoint can be used to view accounts owned by a user
     * @param userId
     * @return list of account object
     */
    @GetMapping(path = "/getByUser/{userId}")
    public ResponseEntity viewAccountsByUser(@PathVariable(value = "userId") int userId) {
        try {
            List<Account> accounts = accountService.getAccountsByUserId(userId);
            return new ResponseEntity<List<Account>>(accounts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
