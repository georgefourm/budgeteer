package com.georgesdoe.budgeteer.member.web;

import com.georgesdoe.budgeteer.common.domain.ResourceNotFoundException;
import com.georgesdoe.budgeteer.member.domain.Member;
import com.georgesdoe.budgeteer.member.domain.MemberService;
import com.georgesdoe.budgeteer.common.web.SimpleMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
public class MemberController {

    @Autowired
    MemberService members;

    @GetMapping("/members")
    public List<Member> list() {
        return members.getMembers();
    }

    @PostMapping("/members")
    public Member create(@Valid @RequestBody MemberRequest request) {
        return members.createMember(request);
    }

    @PutMapping("/members/{id}")
    public Member update(@PathVariable Long id, @RequestBody MemberRequest request) throws ResourceNotFoundException {
        return members.updateMember(id, request);
    }

    @DeleteMapping("/members/{id}")
    public SimpleMessageResponse delete(@PathVariable Long id) throws ResourceNotFoundException {
        members.deleteMember(id);
        return new SimpleMessageResponse("Member deleted");
    }
}
