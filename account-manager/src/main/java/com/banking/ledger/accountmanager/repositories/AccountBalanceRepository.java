package com.banking.ledger.accountmanager.repositories;

import com.banking.ledger.accountmanager.beans.Account;
import com.banking.ledger.accountmanager.beans.AccountBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountBalanceRepository extends JpaRepository<AccountBalance,Integer> {

    AccountBalance findByAccountNumberAndRecordStatus(String accountNumber, int recordStatus);

    List<AccountBalance> findByAccountNumber(String accountNumber);
}
