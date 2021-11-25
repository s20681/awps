package com.pjatk.awps.service;

import com.pjatk.awps.model.AppUser;
import com.pjatk.awps.model.PersonalData;
import com.pjatk.awps.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{
    UserRepository userRepository;
    PersonService personService;

    @Autowired
    public UserService(UserRepository userRepository, PersonService personService) {
        this.userRepository = userRepository;
        this.personService = personService;
    }

    public AppUser save(AppUser appUser){
        if(appUser.getPerson() == null){
            userRepository.save(appUser);
            appUser.setPerson(new PersonalData());
        }

        personService.save(appUser.getPerson());
        appUser.getPerson().setAppUser(appUser);
        return userRepository.save(appUser);
    }

    public List<AppUser> saveAll(List<AppUser> appUserList){
        return userRepository.saveAll(appUserList);
    }

    public List<AppUser> getList(){
        return userRepository.findAll();
    }

    public AppUser getSample(){
        return userRepository.findAll().get(0);
    }
}
