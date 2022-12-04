package com.georgesdoe.budgeteer.web.api;

import com.georgesdoe.budgeteer.domain.importing.ImporterService;
import com.georgesdoe.budgeteer.domain.importing.parsing.FileParserConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImportController {

    @Autowired
    ImporterService service;

    @PostMapping(value = "/import", consumes = "multipart/form-data")
    public void importFile(
            @RequestPart("configuration") FileParserConfiguration configuration,
            @RequestPart("file") MultipartFile file
    ) {
        service.importFile(file, configuration);
    }
}
