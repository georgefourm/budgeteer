package com.georgesdoe.budgeteer.domain.income;

import com.georgesdoe.budgeteer.domain.expense.Category;
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

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    @CreationTimestamp
    Instant createdAt;
}
