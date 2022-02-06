package com.banking.ledger.accountmanager.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "bl_account_balance")
@Data
@NoArgsConstructor
public class AccountBalance {

    @Id
    @Column(name = "acc_balance_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accBalanceId;

    @Column(name = "account_id")
    private String accountNumber;

    @Column(name = "tr_amount")
    private Double transactionAmount;

    @Column(name = "balance")
    private Double accBalance;

    @Column(name = "transaction_id")
    private int transactionId;

    @Column(name = "record_status")
    private int recordStatus;
}
