package com.georgesdoe.budgeteer.web.api;

import com.georgesdoe.budgeteer.domain.importing.ImportConfiguration;
import com.georgesdoe.budgeteer.domain.importing.ImporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
public class ImportController {

    @Autowired
    ImporterService service;

    @PostMapping(value = "/import", consumes = "multipart/form-data")
    public ResponseEntity<Map<String, ImporterService.ImportResult>> importFile(
            @RequestPart("configuration") ImportConfiguration configuration,
            @RequestPart("file") MultipartFile file
    ) {
        var transactions = service.importFile(file, configuration);
        return ResponseEntity.ok(Map.of("transactions", transactions));
    }
}
