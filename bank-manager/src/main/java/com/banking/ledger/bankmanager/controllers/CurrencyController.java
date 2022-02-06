package com.banking.ledger.bankmanager.controllers;

import com.banking.ledger.bankmanager.beans.Currency;
import com.banking.ledger.bankmanager.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @GetMapping(path = "/get/{currencyCode}")
    public ResponseEntity getCurrencyByCode(@PathVariable(value = "currencyCode") String currencyCode) {
        try {
            Currency currency = currencyService.getCurrencyByCode(currencyCode);
            return new ResponseEntity<Currency>(currency, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/create")
    public Currency createCurrency(@RequestBody Currency currency) {
        return currencyService.createCurrency(currency);
    }

    @PutMapping(path = "/update")
    public Currency updateCurrency(@RequestBody Currency currency) {
        return currencyService.updateCurrency(currency);
    }

    @GetMapping(path = "/getHistory/{currencyCode}")
    public ResponseEntity viewCurrencyHistory(@PathVariable(value = "currencyCode") String currencyCode) {
        try {
            List<Currency> currencyHistory = currencyService.getCurrencyHistory(currencyCode);
            return new ResponseEntity<List<Currency>>(currencyHistory, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
