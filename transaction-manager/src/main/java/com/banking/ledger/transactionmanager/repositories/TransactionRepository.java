package com.banking.ledger.transactionmanager.repositories;

import com.banking.ledger.transactionmanager.beans.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    List<Transaction> getByTransactionUserAndRecordStatus(int userId, int recordStatus);

    Transaction getByTransactionReferenceAndRecordStatus(int transactionRef, int recordStatus);

    List<Transaction> getByTransactionReference(int userId);

}
