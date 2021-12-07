package com.pjatk.awps.service;

import com.pjatk.awps.model.Address;
import com.pjatk.awps.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    PersonRepository PersonRepository;

    @Autowired
    public PersonService(PersonRepository PersonRepository) {
        this.PersonRepository = PersonRepository;
    }

    public Address save(Address address){
        if(address == null){
            address = new Address();
        }
        return PersonRepository.save(address);
    }

    public Address getSample(){
        return PersonRepository.findAll().get(0);
    }
}
