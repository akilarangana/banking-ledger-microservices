package com.banking.ledger.bankmanager.repositories;

import com.banking.ledger.bankmanager.beans.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency,Integer> {

    List<Currency> findByCurrencyCode(String currencyCode);
    Currency findByCurrencyCodeAndRecordStatus(String currencyCode,int recordStatus);
}
