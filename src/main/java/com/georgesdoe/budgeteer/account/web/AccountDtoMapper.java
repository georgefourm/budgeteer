package com.georgesdoe.budgeteer.account.web;

import com.georgesdoe.budgeteer.account.domain.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountDtoMapper {
    @Mapping(target = "id", ignore = true)
    Account toDomain(AccountRequestDto dto);

    AccountResponseDto toResponse(Account account);
}
