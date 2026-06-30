package com.georgesdoe.budgeteer.transaction.repository;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /**
     * Signed amount: positive values are incomes, negative values are expenses.
     */
    BigDecimal amount;

    String description;

    Instant transactionTs;

    Long accountId;

    Long categoryId;

    @CreationTimestamp
    Instant createdAt;
}
