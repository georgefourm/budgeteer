package com.georgesdoe.budgeteer.importing.domain;

import com.georgesdoe.budgeteer.category.domain.CategoryResolver;
import com.georgesdoe.budgeteer.importing.domain.parsing.FileParserFactory;
import com.georgesdoe.budgeteer.transaction.domain.Transaction;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImporterService {

    FileParserFactory factory = new FileParserFactory();

    @Autowired
    CategoryResolver categoryResolver;

    public static class ImportResult {
        @Getter
        List<Transaction> transactions;
    }

    public ImportResult importFile(MultipartFile file, ImportConfiguration configuration) {
        var parser = factory.getFromFile(file);
        var parsed = parser.parseFile(file, configuration.fileConfiguration);

        var transactions = new ArrayList<Transaction>();
        for (var entry : parsed) {
            transactions.add(parseTransaction(entry, configuration));
        }

        var result = new ImportResult();
        result.transactions = transactions;
        return result;
    }

    protected Transaction parseTransaction(ImportedTransaction entry, ImportConfiguration configuration) {
        var transaction = new Transaction();

        transaction.setAmount(entry.getValue());
        transaction.setTransactionTs(entry.getTimestamp());
        transaction.setDescription(entry.getDescription());
        transaction.setAccountId(configuration.accountId);

        var category = categoryResolver.resolveByName(entry.getCategory());
        if (category.isEmpty()) {
            category = categoryResolver.resolveByDescription(entry.getDescription());
        }
        category.ifPresent(v -> transaction.setCategoryId(v.getId()));

        return transaction;
    }
}
