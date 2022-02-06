package com.banking.ledger.accountmanager.services;

import com.banking.ledger.accountmanager.beans.Account;
import com.banking.ledger.accountmanager.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class AccountService {

    private static int ACC_NUMBER_PREFIX = 10000;

    @Autowired
    AccountRepository accountRepository;

    public Account getAccountById(String accountNumber){
        return accountRepository.findByAccountNumberAndRecordStatus(accountNumber,0);
    }

    public Account createAccount(Account account){
        account.setRecordStatus(0);
        Account createdAccount =  accountRepository.saveAndFlush(account);
        createdAccount.setAccountNumber("ACC" + 10000 + createdAccount.getAccountId());
        return accountRepository.saveAndFlush(createdAccount);
    }

    public Account updateAccount(Account account){
        Account originalAccount = accountRepository.findByAccountNumberAndRecordStatus(account.getAccountNumber(),0);
        if(originalAccount != null){
            account.setAccountId(0);
            account.setRecordStatus(0);
            originalAccount.setRecordStatus(1);
            Account createdBank =  accountRepository.saveAndFlush(account);
            accountRepository.saveAndFlush(originalAccount);
            return createdBank;
        }
        else{
            return createAccount(account);
        }
    }

    public List<Account> getAccountHistory(String accountNumber){
        return accountRepository.findByAccountNumber(accountNumber);
    }

    public List<Account> getAccountsByUserId(int userId){
        return accountRepository.findByUserIdAndRecordStatus(userId,0);
    }
}
