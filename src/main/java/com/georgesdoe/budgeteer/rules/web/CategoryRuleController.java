package com.georgesdoe.budgeteer.rules.web;

import com.georgesdoe.budgeteer.common.domain.ResourceNotFoundException;
import com.georgesdoe.budgeteer.common.web.SimpleMessageResponse;
import com.georgesdoe.budgeteer.rules.domain.CategoryRuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Category Rules", description = "Manage the rules that classify transactions into categories during import.")
@RestController
public class CategoryRuleController {

    @Autowired
    CategoryRuleService rules;

    @Autowired
    CategoryRuleDtoMapper mapper;

    @Operation(summary = "List all category rules")
    @GetMapping("/rules")
    public List<CategoryRuleResponseDto> index() {
        return rules.listRules().stream().map(mapper::toResponse).toList();
    }

    @Operation(summary = "Create a new category rule")
    @ApiResponse(responseCode = "200", description = "Category rule created")
    @ApiResponse(responseCode = "404", description = "Referenced category not found", content = @Content)
    @PostMapping("/rules")
    public CategoryRuleResponseDto create(@RequestBody CategoryRuleRequestDto request)
            throws ResourceNotFoundException {
        return mapper.toResponse(rules.createRule(mapper.toDomain(request)));
    }

    @Operation(summary = "Update an existing category rule")
    @ApiResponse(responseCode = "200", description = "Category rule updated")
    @ApiResponse(responseCode = "404", description = "Category rule or referenced category not found", content = @Content)
    @PutMapping("/rules/{id}")
    public CategoryRuleResponseDto update(@PathVariable Long id,
                                          @RequestBody CategoryRuleRequestDto request)
            throws ResourceNotFoundException {
        return mapper.toResponse(rules.updateRule(id, mapper.toDomain(request)));
    }

    @Operation(summary = "Delete a category rule")
    @ApiResponse(responseCode = "200", description = "Category rule deleted")
    @ApiResponse(responseCode = "404", description = "Category rule not found", content = @Content)
    @DeleteMapping("/rules/{id}")
    public SimpleMessageResponse delete(@PathVariable Long id) throws ResourceNotFoundException {
        rules.deleteRule(id);
        return new SimpleMessageResponse("Category rule deleted");
    }

    @Operation(summary = "Apply a rule to all existing transactions",
            description = "Assigns this rule's category to every transaction whose description matches the rule")
    @ApiResponse(responseCode = "200", description = "Rule applied")
    @ApiResponse(responseCode = "404", description = "Category rule not found", content = @Content)
    @PostMapping("/rules/{id}/apply")
    public ApplyRuleResponseDto apply(@PathVariable Long id) throws ResourceNotFoundException {
        return new ApplyRuleResponseDto(rules.applyRule(id));
    }
}
