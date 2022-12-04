package com.georgesdoe.budgeteer.domain.importing.parsing;

import com.georgesdoe.budgeteer.domain.importing.FileImportException;
import org.springframework.web.multipart.MultipartFile;

public class FileParserFactory {
    static final String XLS_TYPE = "application/vnd.ms-excel";

    public FileParser getFromFile(MultipartFile file) {
        if (file.getContentType() != null && file.getContentType().equals(XLS_TYPE)) {
            return new XlsFileParser();
        }
        throw new FileImportException("Invalid or unsupported file type: " + file.getContentType());
    }
}
