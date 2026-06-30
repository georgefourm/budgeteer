package com.georgesdoe.budgeteer.account.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

@Schema(description = "Payload for creating or updating an account.")
@Data
public class AccountRequestDto {
    @Schema(description = "Display name of the account.", example = "Checking Account")
    @NotBlank(message = "The account name must be provided")
    String name;
}
