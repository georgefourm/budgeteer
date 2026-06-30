package com.georgesdoe.budgeteer.account.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "An account that transactions can be booked against.")
@Data
public class AccountResponseDto {
    @Schema(description = "Unique identifier of the account.", example = "1")
    Long id;

    @Schema(description = "Display name of the account.", example = "Checking Account")
    String name;
}
