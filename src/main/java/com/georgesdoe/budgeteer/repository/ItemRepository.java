package com.georgesdoe.budgeteer.repository;

import com.georgesdoe.budgeteer.domain.expense.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
