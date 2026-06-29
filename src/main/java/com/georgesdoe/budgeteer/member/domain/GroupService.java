package com.georgesdoe.budgeteer.member.domain;

import com.georgesdoe.budgeteer.common.domain.ResourceNotFoundException;
import com.georgesdoe.budgeteer.member.repository.GroupRepository;
import com.georgesdoe.budgeteer.member.repository.MemberRepository;
import com.georgesdoe.budgeteer.member.web.GroupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class GroupService {

    @Autowired
    GroupRepository groups;

    @Autowired
    MemberRepository members;

    public List<Group> getGroups() {
        return (List<Group>) groups.findAll(Sort.by("name"));
    }

    public Group createGroup(GroupRequest request) throws ResourceNotFoundException {
        var group = new Group();
        group.setName(request.getName());
        group.setMembers(new HashSet<>());

        if (request.getMemberIds() != null && request.getMemberIds().size() > 0) {
            var memberList = (List<Member>) members.findAllById(request.getMemberIds());
            if (memberList.isEmpty()) {
                throw new ResourceNotFoundException(Member.class);
            }
            group.members.addAll(memberList);
        }
        groups.save(group);
        return group;
    }

    public Group updateGroup(Long groupId, GroupRequest request) throws ResourceNotFoundException {
        var group = groups.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException(Group.class));
        group.setName(request.getName());
        if (request.getMemberIds() != null && request.getMemberIds().size() > 0) {
            var memberList = (List<Member>) members.findAllById(request.getMemberIds());
            if (memberList.isEmpty()) {
                throw new ResourceNotFoundException(Member.class);
            }
            group.members.addAll(memberList);
        }
        if (request.getMemberIds() != null && request.getMemberIds().isEmpty()) {
            group.members.removeIf(member -> true);
        }
        groups.save(group);
        return group;
    }

    public void deleteGroup(Long groupId) throws ResourceNotFoundException {
        var group = groups.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException(Group.class));
        group.members.removeIf(member -> true);
        groups.delete(group);
    }
}
