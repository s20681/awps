package com.pjatk.awps.service;

import com.pjatk.awps.model.Destination;
import com.pjatk.awps.model.Group;
import com.pjatk.awps.model.enums.Location;
import com.pjatk.awps.repository.DestinationRepository;
import com.pjatk.awps.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class DestinationService {
    DestinationRepository destinationRepository;
    UserService userService;
    EntityManager entityManager;

    public DestinationService(DestinationRepository destinationRepository, UserService userService, EntityManager entityManager) {
        this.destinationRepository = destinationRepository;
        this.userService = userService;
        this.entityManager = entityManager;
    }

    public Destination save(Destination destination){
        return destinationRepository.save(destination);
    }

    public List<Destination> getList(){
        return destinationRepository.findAll();
    }

    public Destination getSample(){
        return destinationRepository.findAll().get(0);
    }
}
