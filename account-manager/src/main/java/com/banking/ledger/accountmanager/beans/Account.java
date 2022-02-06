package com.banking.ledger.accountmanager.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "bl_account")
@Data
@NoArgsConstructor
public class Account {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "bank_id")
    private int bankId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "account_type")
    private int accountType;

    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "record_status")
    private int recordStatus;
}
