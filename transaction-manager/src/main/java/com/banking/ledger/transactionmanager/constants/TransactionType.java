package com.banking.ledger.transactionmanager.constants;

public enum TransactionType {

    CREDIT(1),
    DEBIT(2);

    public final int type;

    TransactionType(int type) {
        this.type = type;
    }

    public int getValue() {
        return type;
    }
}
