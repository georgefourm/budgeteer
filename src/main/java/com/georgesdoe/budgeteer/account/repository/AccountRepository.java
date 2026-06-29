package com.georgesdoe.budgeteer.account.repository;

import com.georgesdoe.budgeteer.account.domain.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
