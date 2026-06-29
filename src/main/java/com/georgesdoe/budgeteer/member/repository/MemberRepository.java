package com.georgesdoe.budgeteer.member.repository;

import com.georgesdoe.budgeteer.member.domain.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MemberRepository extends CrudRepository<Member, Long>, PagingAndSortingRepository<Member, Long> {
}