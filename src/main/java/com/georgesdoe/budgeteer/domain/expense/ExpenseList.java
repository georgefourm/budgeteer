package com.georgesdoe.budgeteer.domain.expense;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "expense_lists")
public class ExpenseList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    BigDecimal total;

    LocalDate listDate;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "list_id")
    List<Expense> expenses = new ArrayList<>();

    public void updateTotal() {
        BigDecimal total = expenses
                .stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
        setTotal(total);
    }
}
