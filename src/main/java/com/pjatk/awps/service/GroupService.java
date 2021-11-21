package com.pjatk.awps.service;

import com.pjatk.awps.model.Group;
import com.pjatk.awps.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
    GroupRepository groupRepository;
    UserService userService;

    @Autowired
    public GroupService(GroupRepository groupRepository, UserService userService) {
        this.groupRepository = groupRepository;
        this.userService = userService;
    }

    public Group save(Group group){
        if(group.getUsers() != null){
            userService.saveAll(group.getUsers());
        }
        return groupRepository.save(group);
    }

    public Group getSample(){
        return groupRepository.findAll().get(0);
    }
}
