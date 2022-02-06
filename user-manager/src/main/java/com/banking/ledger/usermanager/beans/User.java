package com.banking.ledger.usermanager.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "bl_user")
@Data
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "user_id ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "address")
    private String address;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "base_currency")
    private String baseCurrency;
}
