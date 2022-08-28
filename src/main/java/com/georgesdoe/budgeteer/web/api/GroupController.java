package com.georgesdoe.budgeteer.web.api;

import com.georgesdoe.budgeteer.domain.common.ResourceNotFoundException;
import com.georgesdoe.budgeteer.domain.member.Group;
import com.georgesdoe.budgeteer.domain.member.GroupService;
import com.georgesdoe.budgeteer.web.request.GroupRequest;
import com.georgesdoe.budgeteer.web.response.SimpleMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GroupController {

    @Autowired
    GroupService groups;

    @GetMapping("/groups")
    public List<Group> list() {
        return groups.getGroups();
    }

    @PostMapping("/groups")
    public Group create(@Valid @RequestBody GroupRequest request) throws ResourceNotFoundException {
        return groups.createGroup(request);
    }

    @PutMapping("/groups/{id}")
    public Group update(@PathVariable Long id, @RequestBody GroupRequest request) throws ResourceNotFoundException {
        return groups.updateGroup(id, request);
    }

    @DeleteMapping("/groups/{id}")
    public SimpleMessageResponse delete(@PathVariable Long id) throws ResourceNotFoundException {
        groups.deleteGroup(id);
        return new SimpleMessageResponse("Group deleted");
    }
}
