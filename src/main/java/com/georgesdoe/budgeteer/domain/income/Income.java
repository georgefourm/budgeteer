package com.georgesdoe.budgeteer.domain.income;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

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
    Type type;

    @CreationTimestamp
    OffsetDateTime createdAt;
}
