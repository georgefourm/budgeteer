package com.georgesdoe.budgeteer.domain.importing;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class ImportedTransaction {
    BigDecimal value;

    Instant timestamp;

    String category = "";

    String description;
}
