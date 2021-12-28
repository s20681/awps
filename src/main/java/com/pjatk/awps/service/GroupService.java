package com.pjatk.awps.service;

import com.pjatk.awps.exception.ApiRequestException;
import com.pjatk.awps.model.AppUser;
import com.pjatk.awps.model.Destination;
import com.pjatk.awps.model.Group;
import com.pjatk.awps.model.enums.Role;
import com.pjatk.awps.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@Service
public class GroupService {
    GroupRepository groupRepository;
    DestinationService destinationService;
    UserService userService;
    ScheduleService scheduleService;
    EntityManager entityManager;

    @Autowired
    public GroupService(GroupRepository groupRepository, DestinationService destinationService, UserService userService, ScheduleService scheduleService, EntityManager entityManager) {
        this.groupRepository = groupRepository;
        this.destinationService = destinationService;
        this.userService = userService;
        this.scheduleService = scheduleService;
        this.entityManager = entityManager;
    }

    public Group save(Group group){
        if(group.getAppUsers() != null){
            userService.saveAll(group.getAppUsers());
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

        AppUser appUser = (AppUser) httpSession.getAttribute("user");
        addUser(group, appUser);
        setAllUsersRole(Role.Passenger, group);
        scheduleService.create(httpSession, group);

        save(group);
        return group;
    }

    public Group join(HttpSession httpSession, Long groupId){
        if(httpSession.getAttribute("user") == null){
            throw new ApiRequestException("You must be logged in to join a group");
        }

        AppUser appUser = userService.getById((Long) httpSession.getAttribute("userid"));
        if(appUser == null){
            throw new ApiRequestException("User data error");
        }

        Group group = get(httpSession, groupId);
        if(group == null){
            throw new ApiRequestException("Group data error");
        }

        group.getAppUsers().add(appUser);

        return new Group();
    }

    public void addUser(Group group, AppUser appUser){
        group.getAppUsers().add(appUser);
        group.getRoleMap().put(appUser.getId(), Role.None);
    }

    public Group get(HttpSession httpSession, Long groupId){
        if(httpSession.getAttribute("user") == null){
            throw new ApiRequestException("You must be logged in to browse groups");
        }


        return groupRepository.getById(groupId);
    }

    public void setAllUsersRole(Role role, Group group){
        Set<Long> ids = group.getRoleMap().keySet();
        for (Long id :
                ids) {
            group.getRoleMap().replace(id, role);
        }

    }

    public List<Group> getList(){
        return groupRepository.findAll();
    }

    public Group getSample(){
        return groupRepository.findAll().get(0);
    }
}
