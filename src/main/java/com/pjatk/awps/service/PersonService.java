package com.pjatk.awps.service;

import com.pjatk.awps.model.PersonalData;
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

    public PersonalData save(PersonalData personalData){
        if(personalData == null){
            personalData = new PersonalData();
        }
        return PersonRepository.save(personalData);
    }

    public PersonalData getSample(){
        return PersonRepository.findAll().get(0);
    }
}
