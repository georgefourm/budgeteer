package com.georgesdoe.budgeteer.repository;

import com.georgesdoe.budgeteer.domain.member.Member;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MemberRepository extends PagingAndSortingRepository<Member, Long> {
}