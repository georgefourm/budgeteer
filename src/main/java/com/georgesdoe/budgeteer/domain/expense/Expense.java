package com.georgesdoe.budgeteer.domain.expense;

import com.georgesdoe.budgeteer.domain.member.Group;
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

    @ManyToOne
    @JoinColumn(name = "item_id")
    Item item;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    @ManyToOne
    @JoinColumn(name = "group_id")
    Group group;

    BigDecimal cost;

    String description;

    Integer amount;

    Instant boughtAt;

    @CreationTimestamp
    Instant createdAt;
}
