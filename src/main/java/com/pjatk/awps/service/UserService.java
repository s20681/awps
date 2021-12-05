package com.pjatk.awps.service;

import com.pjatk.awps.exception.ApiRequestException;
import com.pjatk.awps.model.AppUser;
import com.pjatk.awps.model.PersonalData;
import com.pjatk.awps.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Iterator;
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

    public ResponseEntity<?> register(AppUser appUser){
        if(appUser.getLogin().isEmpty() || appUser.getPassword().isEmpty() || appUser.getEmail().isEmpty()){
            throw new ApiRequestException("Login, password and email fields cannot be empty!");
        }

        if(userRepository.findByLogin(appUser.getLogin()) != null){
            throw new ApiRequestException("This login already exists!");
        }

        if(appUser.getPassword().length() < 8){
            throw new ApiRequestException("Password should be at least 8 characters long.");
        }

        return ResponseEntity.ok(save(appUser));
    }

    public ResponseEntity<?> login(HttpSession httpSession, AppUser appUser){
        appUser = userRepository.findByLoginAndPassword(appUser.getLogin(), appUser.getPassword());
        if(appUser == null)
            throw new ApiRequestException("No such user");
        else {
            //should append session only with serializable objects, for further inspection.
            httpSession.setAttribute("user", appUser);
            httpSession.setAttribute("userid", appUser.getId());
            httpSession.setAttribute("logged_in", true);

            return ResponseEntity.ok(appUser);
        }
    }

    public ResponseEntity<?> logout(HttpSession httpSession){
        httpSession.setAttribute("user", null);
        httpSession.setAttribute("userid", null);
        httpSession.setAttribute("logged_in", false);
        return ResponseEntity.ok(null);
    }

    public String sessionData(HttpSession httpSession){
        String output = "";
        Enumeration<String> enums = httpSession.getAttributeNames();
        for (Iterator<String> it = enums.asIterator(); it.hasNext(); ) {
            String attrib = it.next();
            output = output +" "+ attrib;
            output = output +" "+ httpSession.getAttribute(attrib).toString() +"\n";
        }
        return output;
    }

    public AppUser save(AppUser appUser){
        if(userRepository.findByLogin(appUser.getLogin()) != null){
            System.err.println("USER LOGIN ALREADY TAKEN!");
            return null;
        }

        if(appUser.getPerson() == null){
            userRepository.save(appUser);
            appUser.setPerson(new PersonalData());
        }

        personService.save(appUser.getPerson());
        appUser.getPerson().setAppUser(appUser);
        return userRepository.save(appUser);
    }

    public ResponseEntity<?> getByLogin(String login){
        AppUser appUser = userRepository.findByLogin(login);
        if(appUser == null){
            throw new ApiRequestException("Login not found.");
        }
        return ResponseEntity.ok(userRepository.findByLogin(login));
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
