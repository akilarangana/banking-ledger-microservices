package com.banking.ledger.transactionmanager.constants;

public enum TransactionStatus {

        SUCCESS(1),
        PENDING(2),
        REJECTED(3);

        public final int status;

    TransactionStatus(int status) {
            this.status = status;
        }

        public int getValue() {
            return status;
        }

}
