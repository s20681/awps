package com.pjatk.awps.service;

import com.pjatk.awps.exception.ApiRequestException;
import com.pjatk.awps.model.Destination;
import com.pjatk.awps.model.Group;
import com.pjatk.awps.model.enums.DestinationType;
import com.pjatk.awps.model.enums.Location;
import com.pjatk.awps.repository.DestinationRepository;
import com.pjatk.awps.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

        if(destination.getLocationEnum() == null){
            throw new ApiRequestException("Please provide a location of the destination enum: None, Aniołki, Brętowo, Brzeźno, Chełm, Jasień,\n" +
                    "    Kokoszki, Letnica, Matarnia, Młyniska, Nowy_Port, Oliwa, Olszynka, Orunia_Górna_Gdańsk_Południe, Orunia_Św_Wojciech_Lipce,\n" +
                    "    Osowa, Piecki_Migowo, Przeróbka, Przymorze_Małe, Przymorze_Wielkie, Rudniki, Siedlce, Stogi, Strzyża, Suchanino, Śródmieście, Ujeścisko_Łostowice,\n"+
                    "    VII_Dwór, Wrzeszcz_Dolny, Wrzeszcz_Górny, Wyspa_Sobieszewska, Wzgórze_Mickiewicza, Zaspa_Młyniec, Zaspa_Rozstaje, Żabianka_Wejhera_Jelitkowo_Tysiąclecia");
        }


        if(destination.getDestinationTypeEnum() == DestinationType.NONE){
            throw new ApiRequestException("Please provide with destination type enum: SCHOOL, WORKPLACE OR MEETING_POINT");
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
