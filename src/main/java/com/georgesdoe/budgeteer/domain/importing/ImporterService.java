package com.georgesdoe.budgeteer.domain.importing;

import com.georgesdoe.budgeteer.domain.expense.Expense;
import com.georgesdoe.budgeteer.domain.importing.parsing.FileParserFactory;
import com.georgesdoe.budgeteer.domain.income.Income;
import com.georgesdoe.budgeteer.repository.ExpenseRepository;
import com.georgesdoe.budgeteer.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public class ImporterService {

    FileParserFactory factory = new FileParserFactory();

    @Autowired
    ExpenseRepository expenseRepo;

    @Autowired
    IncomeRepository incomeRepo;

    @Autowired
    CategoryRuleService ruleService;

    public void importFile(MultipartFile file, ImportConfiguration configuration) {
        var parser = factory.getFromFile(file);
        var transactions = parser.parseFile(file, configuration.fileConfiguration);

        var incomes = new ArrayList<Income>();
        var expenses = new ArrayList<Expense>();

        for (var transaction : transactions) {
            if (transaction.getValue().compareTo(BigDecimal.ZERO) >= 0) {
                var income = parseIncome(transaction);
                income.setMemberId(configuration.memberId);
                incomes.add(income);
            } else {
                var expense = parseExpense(transaction);
                expense.setMemberId(configuration.memberId);
                expenses.add(expense);
            }
        }
        expenseRepo.saveAll(expenses);
        incomeRepo.saveAll(incomes);
    }

    protected Income parseIncome(ImportedTransaction transaction) {
        var income = new Income();
        income.setAmount(transaction.getValue());
        income.setReceivedAt(transaction.getTimestamp());
        income.setDescription(transaction.getDescription());

        var rule = ruleService.findApplicableRule(transaction);
        if (rule != null) {
            income.setCategory(rule.getCategory());
            if (rule.getDescriptionReplacement() != null) {
                income.setDescription(rule.getDescriptionReplacement());
            }
        }

        return income;
    }

    protected Expense parseExpense(ImportedTransaction transaction) {
        var expense = new Expense();
        expense.setAmount(transaction.getValue().abs());
        expense.setBoughtAt(transaction.getTimestamp());
        expense.setDescription(transaction.getDescription());

        var rule = ruleService.findApplicableRule(transaction);
        if (rule != null) {
            expense.setCategory(rule.getCategory());
            if (rule.getDescriptionReplacement() != null) {
                expense.setDescription(rule.getDescriptionReplacement());
            }
        }

        return expense;
    }
}
