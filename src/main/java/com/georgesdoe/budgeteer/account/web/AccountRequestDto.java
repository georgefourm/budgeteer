package com.georgesdoe.budgeteer.account.web;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

@Data
public class AccountRequestDto {
    @NotBlank(message = "The account name must be provided")
    String name;
}
