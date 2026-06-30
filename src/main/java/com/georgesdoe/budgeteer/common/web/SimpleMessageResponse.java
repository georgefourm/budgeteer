package com.georgesdoe.budgeteer.common.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(description = "A simple response carrying a human-readable status message.")
@Data
@AllArgsConstructor
public class SimpleMessageResponse {
    @Schema(description = "Human-readable status message.", example = "Account deleted")
    String message;
}
