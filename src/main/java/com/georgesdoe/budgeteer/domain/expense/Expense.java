package com.georgesdoe.budgeteer.domain.expense;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long groupId;

    Long memberId;

    Long categoryId;

    BigDecimal amount;

    String description;

    Instant boughtAt;

    @CreationTimestamp
    Instant createdAt;
}
