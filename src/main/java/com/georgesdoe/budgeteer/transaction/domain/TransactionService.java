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

    @Autowired
    TransactionEntityMapper mapper;

    public List<Transaction> listTransactions(Direction direction) {
        List<TransactionEntity> entities;
        if (direction == Direction.INCOME) {
            entities = transactions.findByAmountGreaterThan(BigDecimal.ZERO, BY_TRANSACTION_TS_DESC);
        } else if (direction == Direction.EXPENSE) {
            entities = transactions.findByAmountLessThan(BigDecimal.ZERO, BY_TRANSACTION_TS_DESC);
        } else {
            entities = new LinkedList<>();
            transactions.findAll(BY_TRANSACTION_TS_DESC).forEach(entities::add);
        }
        return entities.stream().map(mapper::toDomain).toList();
    }

    public Transaction createTransaction(Transaction transaction) throws ResourceNotFoundException {
        validateReferences(transaction);
        var entity = mapper.toEntity(transaction);
        transactions.save(entity);
        return mapper.toDomain(entity);
    }

    public void createTransactions(List<Transaction> request) throws ResourceNotFoundException {
        var batch = new LinkedList<TransactionEntity>();
        for (Transaction transaction : request) {
            validateReferences(transaction);
            batch.add(mapper.toEntity(transaction));
        }
        transactions.saveAll(batch);
    }

    public Transaction updateTransaction(Long transactionId, Transaction changes)
            throws ResourceNotFoundException {
        var entity = transactions.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException(Transaction.class));
        validateReferences(changes);
        entity.setAmount(changes.getAmount());
        entity.setDescription(changes.getDescription());
        entity.setTransactionTs(changes.getTransactionTs());
        entity.setAccountId(changes.getAccountId());
        entity.setCategoryId(changes.getCategoryId());
        transactions.save(entity);
        return mapper.toDomain(entity);
    }

    public void deleteTransaction(Long transactionId) throws ResourceNotFoundException {
        var entity = transactions.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException(Transaction.class));
        transactions.delete(entity);
    }

    protected void validateReferences(Transaction transaction) throws ResourceNotFoundException {
        if (!accounts.existsById(transaction.getAccountId())) {
            throw new ResourceNotFoundException(Account.class);
        }
        if (transaction.getCategoryId() != null && !categories.existsById(transaction.getCategoryId())) {
            throw new ResourceNotFoundException(Category.class);
        }
    }

    public enum Direction {
        INCOME, EXPENSE
    }
}
