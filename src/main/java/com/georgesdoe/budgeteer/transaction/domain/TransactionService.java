package com.georgesdoe.budgeteer.transaction.domain;

import com.georgesdoe.budgeteer.account.domain.Account;
import com.georgesdoe.budgeteer.account.repository.AccountRepository;
import com.georgesdoe.budgeteer.category.domain.Category;
import com.georgesdoe.budgeteer.category.repository.CategoryRepository;
import com.georgesdoe.budgeteer.common.domain.ResourceNotFoundException;
import com.georgesdoe.budgeteer.transaction.repository.TransactionRepository;
import com.georgesdoe.budgeteer.transaction.web.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Service
public class TransactionService {

    private static final Sort BY_TRANSACTION_TS_DESC = Sort.by(Sort.Direction.DESC, "transactionTs");

    @Autowired
    TransactionRepository transactions;

    @Autowired
    CategoryRepository categories;

    @Autowired
    AccountRepository accounts;

    public List<Transaction> listTransactions(Direction direction) {
        if (direction == Direction.INCOME) {
            return transactions.findByAmountGreaterThan(BigDecimal.ZERO, BY_TRANSACTION_TS_DESC);
        }
        if (direction == Direction.EXPENSE) {
            return transactions.findByAmountLessThan(BigDecimal.ZERO, BY_TRANSACTION_TS_DESC);
        }
        var result = new LinkedList<Transaction>();
        transactions.findAll(BY_TRANSACTION_TS_DESC).forEach(result::add);
        return result;
    }

    public Transaction createTransaction(TransactionRequest request) throws ResourceNotFoundException {
        var transaction = new Transaction();
        populateTransaction(transaction, request);
        transactions.save(transaction);
        return transaction;
    }

    public void createTransactions(List<TransactionRequest> request) throws ResourceNotFoundException {
        var batch = new LinkedList<Transaction>();
        for (TransactionRequest transactionRequest : request) {
            var transaction = new Transaction();
            populateTransaction(transaction, transactionRequest);
            batch.add(transaction);
        }
        transactions.saveAll(batch);
    }

    public Transaction updateTransaction(Long transactionId, TransactionRequest request)
            throws ResourceNotFoundException {
        var transaction = transactions.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException(Transaction.class));
        populateTransaction(transaction, request);
        transactions.save(transaction);
        return transaction;
    }

    public void deleteTransaction(Long transactionId) throws ResourceNotFoundException {
        var transaction = transactions.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException(Transaction.class));
        transactions.delete(transaction);
    }

    protected void populateTransaction(Transaction transaction, TransactionRequest request)
            throws ResourceNotFoundException {
        transaction.setTransactionTs(request.getTransactionTs());
        transaction.setAmount(request.getAmount());
        transaction.setDescription(request.getDescription());

        accounts.findById(request.getAccountId())
                .orElseThrow(() -> new ResourceNotFoundException(Account.class));
        transaction.setAccountId(request.getAccountId());

        if (request.getCategoryId() != null) {
            categories.findById(request.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException(Category.class));
            transaction.setCategoryId(request.getCategoryId());
        }
    }

    public enum Direction {
        INCOME, EXPENSE
    }
}
