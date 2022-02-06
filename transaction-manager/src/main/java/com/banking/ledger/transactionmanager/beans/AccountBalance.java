package com.banking.ledger.transactionmanager.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
public class AccountBalance {

    private String accountNumber;

    private Double transactionAmount;

    private Double accBalance;

    private int transactionId;
}
