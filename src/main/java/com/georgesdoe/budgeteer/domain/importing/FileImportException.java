package com.georgesdoe.budgeteer.domain.importing;

public class FileImportException extends RuntimeException {
    public FileImportException(String message) {
        super(message);
    }

    public FileImportException(Exception e) {
        super(e);
    }
}
