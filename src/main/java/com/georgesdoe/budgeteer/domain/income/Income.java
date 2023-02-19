package com.georgesdoe.budgeteer.domain.income;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity
@Table(name = "incomes")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    BigDecimal amount;

    String description;

    Instant receivedAt;

    Long memberId;

    Long categoryId;

    @CreationTimestamp
    Instant createdAt;
}
