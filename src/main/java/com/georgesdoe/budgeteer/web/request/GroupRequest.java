package com.georgesdoe.budgeteer.web.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class GroupRequest {
    @NotBlank(message = "Name is required")
    String name;

    List<Long> memberIds;
}

