package com.banking.ledger.transactionmanager.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bl_transactions")
@Data
@NoArgsConstructor
public class Transaction {

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;

    @Column(name = "transaction_reference")
    private int transactionReference;

    @Column(name = "transaction_timestamp")
    private Date transactionDateTime;

    @Column(name = "transaction_user")
    private int transactionUser;

    @Column(name = "transaction_amount")
    private Double transactionAmount;

    @Column(name = "transaction_type")
    private int transactionType;

    @Column(name = "origin_acc_number")
    private String originAccNumber;

    @Column(name = "destination_acc_number")
    private String destinationAccNumber;

    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "transaction_status")
    private int transactionStatus;

    @Column(name = "record_status")
    private int recordStatus;
}
