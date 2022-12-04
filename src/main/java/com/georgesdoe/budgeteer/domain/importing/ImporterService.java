package com.georgesdoe.budgeteer.domain.importing;

import com.georgesdoe.budgeteer.domain.importing.parsing.FileParserConfiguration;
import com.georgesdoe.budgeteer.domain.importing.parsing.FileParserFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImporterService {

    FileParserFactory factory = new FileParserFactory();

    public void importFile(MultipartFile file, FileParserConfiguration configuration) {
        var parser = factory.getFromFile(file);
        var transactions = parser.parseFile(file, configuration);
        // TODO: persist transactions
    }
}
