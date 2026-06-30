package com.georgesdoe.budgeteer.importing.web;

import com.georgesdoe.budgeteer.importing.domain.ImportConfiguration;
import com.georgesdoe.budgeteer.importing.domain.ImporterService;
import com.georgesdoe.budgeteer.transaction.web.TransactionDtoMapper;
import com.georgesdoe.budgeteer.transaction.web.TransactionResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Tag(name = "Import", description = "Import transactions from uploaded bank statement files.")
@RestController
public class ImportController {

    @Autowired
    ImporterService service;

    @Autowired
    TransactionDtoMapper transactionMapper;

    @Operation(summary = "Parse a statement file into transactions",
            description = "Parses the uploaded file according to the supplied configuration and returns the "
                    + "resulting transactions (categorized via category rules) without persisting them. "
                    + "Response is keyed by 'transactions'.")
    @PostMapping(value = "/import", consumes = "multipart/form-data")
    public ResponseEntity<Map<String, List<TransactionResponseDto>>> importFile(
            @RequestPart("configuration") ImportConfiguration configuration,
            @RequestPart("file") MultipartFile file
    ) {
        var result = service.importFile(file, configuration);
        var transactions = result.getTransactions().stream()
                .map(transactionMapper::toResponse)
                .toList();
        return ResponseEntity.ok(Map.of("transactions", transactions));
    }
}
