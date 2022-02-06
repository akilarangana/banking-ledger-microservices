package com.banking.ledger.accountmanager.repositories;

import com.banking.ledger.accountmanager.beans.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Integer> {

    Account findByAccountNumberAndRecordStatus(String accountNumber,int recordStatus);

    List<Account> findByAccountNumber(String accountNumber);

    List<Account> findByUserIdAndRecordStatus(int userId,int recordStatus);

}
