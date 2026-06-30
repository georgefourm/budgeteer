package com.georgesdoe.budgeteer.transaction.domain;

import com.georgesdoe.budgeteer.account.domain.Account;
import com.georgesdoe.budgeteer.account.repository.AccountRepository;
import com.georgesdoe.budgeteer.category.domain.Category;
import com.georgesdoe.budgeteer.category.repository.CategoryRepository;
import com.georgesdoe.budgeteer.common.domain.ResourceNotFoundException;
import com.georgesdoe.budgeteer.transaction.repository.TransactionEntity;
import com.georgesdoe.budgeteer.transaction.repository.TransactionEntityMapper;
import com.georgesdoe.budgeteer.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
public class TransactionService {

    private static final Sort DEFAULT_SORTING = Sort.by(Sort.Direction.DESC, "transactionTs");

    private static final int MAX_PAGE_SIZE = 100;

    @Autowired
    TransactionRepository transactions;

    @Autowired
    CategoryRepository categories;

    @Autowired
    AccountRepository accounts;

    @Autowired
    TransactionEntityMapper mapper;

    public Page<Transaction> listTransactions(Pageable pageable) {
        var sorted = PageRequest.of(
                pageable.getPageNumber(),
                Math.min(pageable.getPageSize(), MAX_PAGE_SIZE),
                pageable.getSortOr(DEFAULT_SORTING)
        );
        return transactions.findAll(sorted).map(mapper::toDomain);
    }

    public Transaction createTransaction(Transaction transaction) {
        validateReferences(transaction);
        var entity = mapper.toEntity(transaction);
        transactions.save(entity);
        return mapper.toDomain(entity);
    }

    public void createTransactions(List<Transaction> request) {
        var batch = new LinkedList<TransactionEntity>();
        for (Transaction transaction : request) {
            validateReferences(transaction);
            batch.add(mapper.toEntity(transaction));
        }
        transactions.saveAll(batch);
    }

    public Transaction updateTransaction(Long transactionId, Transaction changes) {
        var entity = transactions.findByIdOrThrow(transactionId);
        validateReferences(changes);
        entity.setAmount(changes.getAmount());
        entity.setDescription(changes.getDescription());
        entity.setTransactionTs(changes.getTransactionTs());
        entity.setAccountId(changes.getAccountId());
        entity.setCategoryId(changes.getCategoryId());
        transactions.save(entity);
        return mapper.toDomain(entity);
    }

    /**
     * Assigns the given category to all transactions whose description matches the regex.
     * A null or blank regex is a no-op (a blank pattern would otherwise match every transaction).
     * Returns the number of transactions updated.
     */
    @Transactional
    public int assignCategoryByDescription(Long categoryId, String regex) {
        if (regex == null || regex.isBlank()) {
            return 0;
        }
        return transactions.assignCategoryByDescriptionMatch(categoryId, regex);
    }

    public void deleteTransaction(Long transactionId) {
        var entity = transactions.findByIdOrThrow(transactionId);
        transactions.delete(entity);
    }

    protected void validateReferences(Transaction transaction) {
        if (!accounts.existsById(transaction.getAccountId())) {
            throw new ResourceNotFoundException(Account.class);
        }
        if (transaction.getCategoryId() != null && !categories.existsById(transaction.getCategoryId())) {
            throw new ResourceNotFoundException(Category.class);
        }
    }
}
