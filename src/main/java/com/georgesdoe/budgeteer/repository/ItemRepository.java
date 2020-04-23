package com.georgesdoe.budgeteer.repository;

import com.georgesdoe.budgeteer.domain.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
