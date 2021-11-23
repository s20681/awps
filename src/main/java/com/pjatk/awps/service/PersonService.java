package com.pjatk.awps.service;

import com.pjatk.awps.model.Person;
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

    public Person save(Person appPerson){
        return PersonRepository.save(appPerson);
    }

    public Person getSample(){
        return PersonRepository.findAll().get(0);
    }
}
