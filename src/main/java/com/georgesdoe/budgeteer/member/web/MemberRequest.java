package com.georgesdoe.budgeteer.member.web;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

@Data
public class MemberRequest {
    @NotBlank(message = "Name is required")
    String name;
}

