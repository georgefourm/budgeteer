package com.georgesdoe.budgeteer.expense.repository;

import com.georgesdoe.budgeteer.expense.domain.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
