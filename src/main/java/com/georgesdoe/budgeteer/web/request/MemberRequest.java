package com.georgesdoe.budgeteer.web.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberRequest {
    @NotBlank(message = "Name is required")
    String name;
}

