package com.georgesdoe.budgeteer.domain.importing.parsing;

import com.georgesdoe.budgeteer.domain.importing.ImportedTransaction;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileParser {
    List<ImportedTransaction> parseFile(MultipartFile file, FileParserConfiguration configuration);
}
