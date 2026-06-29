package com.georgesdoe.budgeteer.importing.domain.parsing;

import com.georgesdoe.budgeteer.importing.domain.ImportedTransaction;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileParser {
    List<ImportedTransaction> parseFile(MultipartFile file, FileParserConfiguration configuration);
}
