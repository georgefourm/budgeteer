package com.georgesdoe.budgeteer.transaction.repository;

import com.georgesdoe.budgeteer.transaction.domain.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionEntityMapper {
    TransactionEntity toEntity(Transaction transaction);

    Transaction toDomain(TransactionEntity entity);
}
