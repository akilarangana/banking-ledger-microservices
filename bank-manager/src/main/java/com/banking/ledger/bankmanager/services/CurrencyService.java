package com.banking.ledger.bankmanager.services;

import com.banking.ledger.bankmanager.beans.Currency;
import com.banking.ledger.bankmanager.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Service
public class CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

    public Currency getCurrencyByCode(String currencyCode) {
        return currencyRepository.findByCurrencyCodeAndRecordStatus(currencyCode, 0);
    }

    public Currency createCurrency(Currency currency) {
        Currency availableCurrency = currencyRepository.findByCurrencyCodeAndRecordStatus(currency.getCurrencyCode(), 0);
        if (availableCurrency != null) {
            return updateCurrency(currency);
        } else {
            currency.setRecordStatus(0);
            currency.setLastUpdatedDateTime(new Date());
            Currency createdCurrency = currencyRepository.saveAndFlush(currency);
            return createdCurrency;
        }
    }

    public Currency updateCurrency(Currency currency) {

        List<Currency> currencyList = currencyRepository.findByCurrencyCode(currency.getCurrencyCode());
        if (currencyList != null) {
            for (Currency currency1 : currencyList) {
                currency1.setRecordStatus(1);
            }
        } else {
            currencyList = new ArrayList<>();
        }
        currency.setCurrencyId(0);

        currency.setRecordStatus(0);
        currency.setLastUpdatedDateTime(new Date());
        currencyList.add(currency);
        currencyRepository.saveAllAndFlush(currencyList);
        return currency;
    }

    public List<Currency> getCurrencyHistory(String currencyCode){
        return currencyRepository.findByCurrencyCode(currencyCode);
    }

}
