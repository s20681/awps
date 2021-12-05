package com.pjatk.awps.service;

import com.pjatk.awps.exception.ApiRequestException;
import com.pjatk.awps.model.AppUser;
import com.pjatk.awps.model.Destination;
import com.pjatk.awps.model.Group;
import com.pjatk.awps.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class GroupService {
    GroupRepository groupRepository;
    DestinationService destinationService;
    UserService userService;
    EntityManager entityManager;

    @Autowired
    public GroupService(GroupRepository groupRepository, DestinationService destinationService, UserService userService, EntityManager entityManager) {
        this.groupRepository = groupRepository;
        this.destinationService = destinationService;
        this.userService = userService;
        this.entityManager = entityManager;
    }

    public Group save(Group group){
        if(group.getUsers() != null){
            userService.saveAll(group.getUsers());
        }

        //placeholder
        group.setDestination(new Destination());
        destinationService.save(group.getDestination());


        return groupRepository.save(group);
    }

    public Group create(HttpSession httpSession, Group group){
        if(httpSession.getAttribute("user") == null){
            throw new ApiRequestException("You must be logged in to create a new group");
        }
        if(group.getName() == null){
            throw new ApiRequestException("Group name cannot be empty.");
        }

        if(group.getDestination() == null){
            throw new ApiRequestException("Destination field cannot be empty");
        }

        group.getUsers().add((AppUser) httpSession.getAttribute("user"));
        return group;
    }

    public List<Group> getList(){
        return groupRepository.findAll();
    }

    public Group getSample(){
        return groupRepository.findAll().get(0);
    }
}
