package com.georgesdoe.budgeteer.account.repository;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "accounts")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
}
