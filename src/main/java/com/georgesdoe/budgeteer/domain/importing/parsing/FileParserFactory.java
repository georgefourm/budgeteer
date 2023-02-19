package com.georgesdoe.budgeteer.domain.importing.parsing;

import com.georgesdoe.budgeteer.domain.importing.FileImportException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class FileParserFactory {
    static final List<String> XLS_TYPES = List.of(
            XlsFileParser.XLS_MIME_TYPE,
            XlsFileParser.XLSX_MIME_TYPE
    );

    public FileParser getFromFile(MultipartFile file) {
        if (file.getContentType() != null && XLS_TYPES.contains(file.getContentType())) {
            return new XlsFileParser();
        }
        throw new FileImportException("Invalid or unsupported file type: " + file.getContentType());
    }
}
