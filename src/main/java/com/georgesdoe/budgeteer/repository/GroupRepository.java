package com.georgesdoe.budgeteer.repository;

import com.georgesdoe.budgeteer.domain.member.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GroupRepository extends CrudRepository<Group, Long>, PagingAndSortingRepository<Group, Long> {
}