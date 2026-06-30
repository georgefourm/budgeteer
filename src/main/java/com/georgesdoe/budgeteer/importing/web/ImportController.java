package com.georgesdoe.budgeteer.importing.web;

import com.georgesdoe.budgeteer.importing.domain.ImportConfiguration;
import com.georgesdoe.budgeteer.importing.domain.ImporterService;
import com.georgesdoe.budgeteer.transaction.web.TransactionDtoMapper;
import com.georgesdoe.budgeteer.transaction.web.TransactionResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
public class ImportController {

    @Autowired
    ImporterService service;

    @Autowired
    TransactionDtoMapper transactionMapper;

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
