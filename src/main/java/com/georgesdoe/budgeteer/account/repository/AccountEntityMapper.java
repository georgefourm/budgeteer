package com.georgesdoe.budgeteer.account.repository;

import com.georgesdoe.budgeteer.account.domain.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountEntityMapper {
    AccountEntity toEntity(Account account);

    Account toDomain(AccountEntity entity);
}
