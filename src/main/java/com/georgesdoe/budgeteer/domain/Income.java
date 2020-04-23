package com.georgesdoe.budgeteer.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "incomes")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    BigDecimal amount;

    String notes;

    OffsetDateTime receivedAt;

    @ManyToOne
    @JoinColumn(name = "type_id")
    IncomeType type;

    @CreationTimestamp
    OffsetDateTime createdAt;
}
