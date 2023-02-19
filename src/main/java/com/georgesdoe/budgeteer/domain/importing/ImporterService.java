package com.georgesdoe.budgeteer.domain.importing;

import com.georgesdoe.budgeteer.domain.expense.Expense;
import com.georgesdoe.budgeteer.domain.importing.parsing.FileParserFactory;
import com.georgesdoe.budgeteer.domain.income.Income;
import com.georgesdoe.budgeteer.domain.rules.CategoryRuleService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImporterService {

    FileParserFactory factory = new FileParserFactory();

    @Autowired
    CategoryRuleService rules;

    public static class ImportResult {
        @Getter
        List<Income> incomes;
        @Getter
        List<Expense> expenses;
    }

    public ImportResult importFile(MultipartFile file, ImportConfiguration configuration) {
        var parser = factory.getFromFile(file);
        var transactions = parser.parseFile(file, configuration.fileConfiguration);

        var incomes = new ArrayList<Income>();
        var expenses = new ArrayList<Expense>();

        for (var transaction : transactions) {
            if (transaction.getValue().compareTo(BigDecimal.ZERO) >= 0
                    && configuration.fileConfiguration.getExpensesAsNegative()) {
                var income = parseIncome(transaction);
                income.setMemberId(configuration.memberId);
                incomes.add(income);
            } else {
                var expense = parseExpense(transaction);
                expense.setMemberId(configuration.memberId);
                expense.setGroupId(configuration.groupId);
                expenses.add(expense);
            }
        }
        var result = new ImportResult();
        result.expenses = expenses;
        result.incomes = incomes;
        return result;
    }

    protected Income parseIncome(ImportedTransaction transaction) {
        var income = new Income();
        income.setAmount(transaction.getValue());
        income.setReceivedAt(transaction.getTimestamp());
        income.setDescription(transaction.getDescription());

        return income;
    }

    protected Expense parseExpense(ImportedTransaction transaction) {
        var expense = new Expense();
        expense.setAmount(transaction.getValue().abs());
        expense.setBoughtAt(transaction.getTimestamp());
        expense.setDescription(transaction.getDescription());

        var category = rules.getCategoryByName(transaction.getCategory());

        if (category.isEmpty()) {
            category = rules.getCategoryByDescription(transaction.getDescription());
            category.ifPresent(value -> expense.setCategoryId(value.getId()));
        } else {
            expense.setCategoryId(category.get().getId());
        }

        return expense;
    }
}
