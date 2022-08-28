package com.georgesdoe.budgeteer.web.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class IncomeRequest {
    BigDecimal amount;

    Long typeId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    OffsetDateTime receivedAt;

    String notes;
}
