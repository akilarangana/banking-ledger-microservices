package com.banking.ledger.bankmanager.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "currency")
@Data
@NoArgsConstructor
public class Currency {

    @Id
    @Column(name = "currency_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int currencyId;

    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "currency_rate")
    private Double currencyRate;

    @Column(name = "last_updated_date_time")
    private Date lastUpdatedDateTime;

    @Column(name = "record_status")
    private int recordStatus;
}
