package com.pjatk.awps.service;

import com.pjatk.awps.exception.ApiRequestException;
import com.pjatk.awps.model.Person;
import com.pjatk.awps.model.Transit;
import com.pjatk.awps.model.TransitAddress;
import com.pjatk.awps.model.TransitUser;
import com.pjatk.awps.model.enums.Role;
import com.pjatk.awps.repository.TransitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransitService {
    private final TransitRepository transitRepository;
    private final UserService userService;
    private final TransitUserService transitUserService;
    private final TransitAddressService transitAddressService;

    @Autowired
    public TransitService(TransitRepository transitRepository, UserService userService, TransitUserService transitUserService, TransitAddressService transitAddressService) {
        this.transitRepository = transitRepository;
        this.userService = userService;
        this.transitUserService = transitUserService;
        this.transitAddressService = transitAddressService;
    }

    public Transit make(){
        return save(new Transit());
    }

    public ResponseEntity<Transit> create(HttpSession httpSession, Transit transit) {
        if(httpSession.getAttribute("user") == null){
            throw new ApiRequestException("You must be logged in to create a new transit");
        }

        return ResponseEntity.ok(save(transit));
    }

    public Transit save(Transit transit) {
        if(transit.getSchedule().isEmpty()){
            transit.getSchedule().add(LocalDateTime.now());
            transit.getSchedule().add(LocalDateTime.now());
            transit.getSchedule().add(LocalDateTime.now());
        }
        return transitRepository.save(transit);
    }

    public List<Transit> getList() {
        return transitRepository.findAll();
    }

    public void delete(Transit transit) {
        transitRepository.delete(transit);
    }

    public void deleteById(Long id) {
        transitRepository.delete(findById(id));
    }

    public ResponseEntity<Transit> addUser(Long transitId, Long personId, Role role){
        Optional<Transit> optionalTransit = transitRepository.findById(transitId);
        if(optionalTransit.isPresent()){
            Transit transit = optionalTransit.get();
            List<TransitUser> transitUsers = transit.getTransitUsers();
            for (TransitUser t :
                    transitUsers) {
                if (t.getPersonId().equals(personId)) {
                    throw new ApiRequestException("This user is already on that transit.");
                }
            }

            if(!userService.findById(personId).isPresent()){
                throw new ApiRequestException("This user doesnt exist.");
            }

            Person person = userService.findById(personId).get();
            TransitUser transitUser = transitUserService.make(transit, person, role);
            TransitAddress transitAddress = transitAddressService.make(transit, person.getAddress());
            transit.getTransitUsers().add(transitUser);
            transit.getTransitAddresses().add(transitAddress);
            transitRepository.save(transit);
            return ResponseEntity.ok(transit);
        }

        throw new ApiRequestException("No transit found.");
    }

    public ResponseEntity<?> join(HttpSession httpSession, Long transitId, String role) {
        Role roleEnum = Role.valueOf(role.toUpperCase());

        Optional<Transit> optionalTransit = transitRepository.findById(transitId);
        TransitUser transitUser = null;
        TransitAddress transitAddress = null;
        if(optionalTransit.isPresent() && httpSession.getAttribute("userid") != null){
            Transit transit = optionalTransit.get();
            if(transit.getTransitUsers().size() == transit.getSeats()){
                throw new ApiRequestException("The transit seems full. Cannot add any new users.");
            }

            for (TransitUser t :
                    transit.getTransitUsers()) {
                if (t.getPersonId() == httpSession.getAttribute("userid")){
                    throw new ApiRequestException("This user is already on.");
                }
            }

            Optional<Person> optionalPerson = userService.findById((Long) httpSession.getAttribute("userid"));
            System.err.println(optionalPerson.get().getId());
            System.err.println(optionalPerson.get().getAddress());
            if(optionalTransit.isPresent()){
                Person person = optionalPerson.get();
                transitUser = transitUserService.make(transit, person, roleEnum);
                transitAddress = transitAddressService.make(transit, person.getAddress());
            }else
                throw new ApiRequestException("Person does not exist ? ");

            transit.getTransitUsers().add(transitUser);
            transit.getTransitAddresses().add(transitAddress);
            save(transit);
            return ResponseEntity.ok(transit);
        }
        return ResponseEntity.ok("Another error. Are you logged in?");
    }

    public ResponseEntity<Transit> leave(HttpSession httpSession, Long transitId) {
        Optional<Transit> optionalTransit = transitRepository.findById(transitId);
        Optional<Person> optionalPerson = userService.findById((Long) httpSession.getAttribute("userid"));
        if(optionalTransit.isPresent() && optionalPerson.isPresent()){
            Transit transit = optionalTransit.get();
            Person person = optionalPerson.get();
            for (TransitUser t :
                    transit.getTransitUsers()) {
                if (t.getPersonId().equals(person.getId())) {
                    transit.getTransitUsers().remove(t);
                    transitUserService.delete(t);
                    save(transit);
                    break;
                }
            }

            for (TransitAddress a :
                    transit.getTransitAddresses()) {
                if (a.getAddress().equals(person.getAddress())) {
                    transit.getTransitAddresses().remove(a);
                    transitAddressService.delete(a.getId());
                    save(transit);
                    break;
                }
            }
            return ResponseEntity.ok(transit);
        }
        throw new ApiRequestException("Something went wrong");
    }

    public Transit findById(Long transitId) {
        Optional<Transit> optionalTransit = transitRepository.findById(transitId);
        if(optionalTransit.isPresent()){
            return transitRepository.findById(transitId).get();
        }

        throw new ApiRequestException("Transit does not exist ? ");
    }
}
