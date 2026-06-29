package com.georgesdoe.budgeteer.account.domain;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
}
