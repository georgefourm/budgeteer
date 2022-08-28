package com.georgesdoe.budgeteer.repository;

import com.georgesdoe.budgeteer.domain.member.Group;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GroupRepository extends PagingAndSortingRepository<Group, Long> {
}