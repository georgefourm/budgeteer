package com.georgesdoe.budgeteer.transaction.web;

import com.georgesdoe.budgeteer.transaction.domain.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionDtoMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Transaction toDomain(TransactionRequestDto dto);

    TransactionResponseDto toResponse(Transaction transaction);
}
