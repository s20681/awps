package com.pjatk.awps.service;

import com.pjatk.awps.model.AppUser;
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
        personService.save(appUser.getPerson());
        return userRepository.save(appUser);
    }

    public List<AppUser> saveAll(List<AppUser> appUserList){
        return userRepository.saveAll(appUserList);
    }

//    public User persist(User user){
//        ????
//    }

    public AppUser getSample(){
        return userRepository.findAll().get(0);
    }
}
