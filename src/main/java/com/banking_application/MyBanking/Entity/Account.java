package com.banking_application.MyBanking.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private Long AccountNumber;
    private String AccountType;
    private String AccountHolderName;
    private Long balance;

}
