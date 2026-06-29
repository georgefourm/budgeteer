package com.georgesdoe.budgeteer.member.domain;

import com.georgesdoe.budgeteer.common.domain.ResourceNotFoundException;
import com.georgesdoe.budgeteer.member.repository.MemberRepository;
import com.georgesdoe.budgeteer.member.web.MemberRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    MemberRepository members;

    public List<Member> getMembers() {
        return (List<Member>) members.findAll(Sort.by("name"));
    }

    public Member createMember(MemberRequest request) {
        var member = new Member();
        member.setName(request.getName());
        members.save(member);
        return member;
    }

    public Member updateMember(Long memberId, MemberRequest request) throws ResourceNotFoundException {
        var member = members.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException(Member.class));
        member.setName(request.getName());
        members.save(member);
        return member;
    }

    public void deleteMember(Long memberId) throws ResourceNotFoundException {
        var member = members.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException(Member.class));
        members.delete(member);
    }
}
