package com.banking.ledger.accountmanager.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "bl_account_type")
@Data
@NoArgsConstructor
public class AccountType {

    @Id
    @Column(name = "account_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountTypeId;

    @Column(name = "account_type_desc")
    private int accountTypeDesc;
}
