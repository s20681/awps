package com.pjatk.awps.service;

import com.pjatk.awps.exception.ApiRequestException;
import com.pjatk.awps.model.Destination;
import com.pjatk.awps.repository.DestinationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
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

    public ResponseEntity<Destination> create(HttpSession httpSession, Destination destination){
        if(httpSession.getAttribute("user") == null){
            throw new ApiRequestException("You must be logged in to create a new destination");
        }

        if(destination.getName() == null){
            throw new ApiRequestException("Please provide a name of the destination");
        }

        if(destination.getLatitude() == 0d | destination.getLongitude() == 0d){
            throw new ApiRequestException("Please provide coords of the destination");
        }

        destinationRepository.save(destination);
        return ResponseEntity.ok(destination);
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
