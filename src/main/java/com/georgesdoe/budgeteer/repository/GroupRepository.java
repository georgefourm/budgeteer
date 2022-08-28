package com.georgesdoe.budgeteer.repository;

import com.georgesdoe.budgeteer.domain.member.Group;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<Group, Long> {
}