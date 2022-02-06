package com.banking.ledger.bankmanager.controllers;

import com.banking.ledger.bankmanager.services.BankService;
import com.banking.ledger.bankmanager.beans.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/banks")
public class BankController {

    @Autowired
    BankService bankService;

    @GetMapping(path = "/get/{bankId}")
    public ResponseEntity getBankById(@PathVariable(value = "bankId") int bankId){
        try{
            Bank bank = bankService.getBankById(bankId);
            return new ResponseEntity<Bank>(bank, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/create")
    public Bank createBank(@RequestBody Bank bank){
        return bankService.createBank(bank);
    }

    @DeleteMapping(path = "/delete/{bankId}")
    public Response deleteBank(@PathVariable(value = "bankId") int bankId){
        return bankService.deleteBankById(bankId);
    }

    @PutMapping(path="/update")
    public Bank updateBank(@RequestBody Bank bank){
        return bank;
    }
}
