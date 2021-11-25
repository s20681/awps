package com.pjatk.awps.service;

import com.pjatk.awps.model.Destination;
import com.pjatk.awps.model.Group;
import com.pjatk.awps.model.enums.Location;
import com.pjatk.awps.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
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

    public Group create(Location location, Destination destination, Long userId){
        return new Group();
    }

    public List<Group> getList(){
        return groupRepository.findAll();
    }

    public Group getSample(){
        return groupRepository.findAll().get(0);
    }
}
