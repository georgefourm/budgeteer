package com.georgesdoe.budgeteer.web.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class IncomeRequest {
    @NotNull
    BigDecimal amount;

    Long typeId;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    OffsetDateTime receivedAt;

    String notes;
}
