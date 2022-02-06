package com.banking.ledger.accountmanager.services;

import com.banking.ledger.accountmanager.beans.AccountBalance;
import com.banking.ledger.accountmanager.repositories.AccountBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class AccountBalanceService {

    @Autowired
    AccountBalanceRepository accountBalanceRepository;

    public AccountBalance getAccBalanceByAccNumber(String accountNumber){
        return accountBalanceRepository.findByAccountNumberAndRecordStatus(accountNumber,0);
    }

    public AccountBalance createOrUpdateAccBalance(AccountBalance accountBalance){
        AccountBalance currentAccBalance = accountBalanceRepository.findByAccountNumberAndRecordStatus(accountBalance.getAccountNumber(),0);
        if(currentAccBalance != null){
            accountBalance.setAccBalanceId(0);
            accountBalance.setRecordStatus(0);
            accountBalance.setAccBalance(currentAccBalance.getAccBalance() + accountBalance.getTransactionAmount());
            currentAccBalance.setRecordStatus(1);
            AccountBalance createdBank =  accountBalanceRepository.saveAndFlush(accountBalance);
            accountBalanceRepository.saveAndFlush(currentAccBalance);
            return createdBank;
        }
        else{
            accountBalance.setRecordStatus(0);
            accountBalance.setAccBalance(accountBalance.getTransactionAmount()); // initial acc balance will be transaction amount
            return accountBalanceRepository.saveAndFlush(accountBalance);
        }
    }

    public List<AccountBalance> viewAccountBalanceHistoryByAccNumber(String accNumber){
        return accountBalanceRepository.findByAccountNumber(accNumber);
    }
}
