package com.banking.ledger.bankmanager.beans;

import javax.persistence.*;

@Entity
@Table(name = "bank")
public class Bank {

    @Id
    @Column(name = "bank_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bankId;

    @Column(name = "bank_identifier")
    private String bankIdentifier;

    @Column(name = "bank_desc")
    private String bankDesc;

    public Bank() {

    }

    public Bank(int bankId,String bankIdentifier,String bankDesc){
        this.bankId = bankId;
        this.bankIdentifier = bankIdentifier;
        this.bankDesc = bankDesc;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getBankIdentifier() {
        return bankIdentifier;
    }

    public void setBankIdentifier(String bankIdentifier) {
        this.bankIdentifier = bankIdentifier;
    }

    public String getBankDesc() {
        return bankDesc;
    }

    public void setBankDesc(String bankDesc) {
        this.bankDesc = bankDesc;
    }
}
