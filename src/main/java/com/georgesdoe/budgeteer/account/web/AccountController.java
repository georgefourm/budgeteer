package com.georgesdoe.budgeteer.account.web;

import com.georgesdoe.budgeteer.account.domain.Account;
import com.georgesdoe.budgeteer.account.repository.AccountRepository;
import com.georgesdoe.budgeteer.common.web.SimpleMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import jakarta.validation.Valid;

@RestController
public class AccountController {

    @Autowired
    AccountRepository accounts;

    @GetMapping("/accounts")
    public Iterable<Account> index() {
        return accounts.findAll();
    }

    @PostMapping("/accounts")
    public Account create(@Valid @RequestBody AccountRequest request) {
        Account account = new Account();
        account.setName(request.getName());
        accounts.save(account);
        return account;
    }

    @PutMapping("/accounts/{id}")
    public Account update(@PathVariable Long id,
                          @Valid @RequestBody AccountRequest request) {
        Account account = accounts.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        account.setName(request.getName());
        accounts.save(account);
        return account;
    }

    @DeleteMapping("/accounts/{id}")
    public SimpleMessageResponse delete(@PathVariable Long id) {
        Account account = accounts.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        accounts.delete(account);
        return new SimpleMessageResponse("Account deleted");
    }
}
