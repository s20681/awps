package com.pjatk.awps.service;

import com.pjatk.awps.model.Person;
import com.pjatk.awps.model.Transit;
import com.pjatk.awps.model.TransitUser;
import com.pjatk.awps.model.enums.Role;
import com.pjatk.awps.repository.TransitUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransitUserService {
    private final TransitUserRepository transitUserRepository;

    @Autowired
    public TransitUserService(TransitUserRepository transitUserRepository) {
        this.transitUserRepository = transitUserRepository;
    }

    public TransitUser make(Transit transit, Person person, Role role){
        TransitUser transitUser = new TransitUser();
        transitUser.setPersonId(person.getId());
        transitUser.setTransit(transit);
        transitUser.setRole(role);
        transitUserRepository.save(transitUser);
        return transitUser;
    }

    public TransitUser save(TransitUser transitUser) {
        return transitUserRepository.save(transitUser);
    }

    public List<TransitUser> findAll() {
        return transitUserRepository.findAll();
    }

    public void delete(TransitUser transitUser) {
        transitUserRepository.delete(transitUser);
    }

    public void delete(Long transitUserId) {
        transitUserRepository.deleteById(transitUserId);
    }
}
