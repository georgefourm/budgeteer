package com.georgesdoe.budgeteer.account.domain;

import com.georgesdoe.budgeteer.account.repository.AccountEntityMapper;
import com.georgesdoe.budgeteer.account.repository.AccountRepository;
import com.georgesdoe.budgeteer.common.domain.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountRepository accounts;

    @Autowired
    AccountEntityMapper mapper;

    public List<Account> listAccounts() {
        var result = new LinkedList<Account>();
        accounts.findAll().forEach(entity -> result.add(mapper.toDomain(entity)));
        return result;
    }

    public Account createAccount(Account account) {
        var entity = mapper.toEntity(account);
        accounts.save(entity);
        return mapper.toDomain(entity);
    }

    public Account updateAccount(Long id, Account changes) throws ResourceNotFoundException {
        var entity = accounts.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Account.class));
        entity.setName(changes.getName());
        accounts.save(entity);
        return mapper.toDomain(entity);
    }

    public void deleteAccount(Long id) throws ResourceNotFoundException {
        var entity = accounts.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Account.class));
        accounts.delete(entity);
    }
}
