package com.georgesdoe.budgeteer.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    Item item;

    BigDecimal cost;

    Integer amount;

    OffsetDateTime boughtAt;

    @CreationTimestamp
    OffsetDateTime createdAt;
}
