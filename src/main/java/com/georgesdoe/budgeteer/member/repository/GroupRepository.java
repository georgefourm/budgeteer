package com.georgesdoe.budgeteer.member.repository;

import com.georgesdoe.budgeteer.member.domain.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GroupRepository extends CrudRepository<Group, Long>, PagingAndSortingRepository<Group, Long> {
}