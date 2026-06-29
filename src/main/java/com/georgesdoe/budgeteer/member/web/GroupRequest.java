package com.georgesdoe.budgeteer.member.web;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Data
public class GroupRequest {
    @NotBlank(message = "Name is required")
    String name;

    List<Long> memberIds;
}

