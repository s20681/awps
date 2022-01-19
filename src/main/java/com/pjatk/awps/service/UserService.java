package com.pjatk.awps.service;

import com.pjatk.awps.exception.ApiRequestException;
import com.pjatk.awps.model.Person;
import com.pjatk.awps.model.Address;
import com.pjatk.awps.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class UserService{
    private final UserRepository userRepository;
    private final AddressService addressService;

    @Autowired
    public UserService(UserRepository userRepository, AddressService addressService) {
        this.userRepository = userRepository;
        this.addressService = addressService;
    }

    public ResponseEntity<?> register(Person person){
        if(person.getLogin().isEmpty() || person.getPassword().isEmpty()){
            throw new ApiRequestException("Login, password and email fields cannot be empty!");
        }

        if(userRepository.findByLogin(person.getLogin()) != null){
            throw new ApiRequestException("This login already exists!");
        }

        if(person.getPassword().length() < 8){
            throw new ApiRequestException("Password should be at least 8 characters long.");
        }

        return ResponseEntity.ok(save(person));
    }

    public ResponseEntity<?> login(HttpSession httpSession, Person person){
        person = userRepository.findByLoginAndPassword(person.getLogin(), person.getPassword());
        if(person == null)
            throw new ApiRequestException("No such user");
        else {
            //should append session only with serializable objects, for further inspection.
            httpSession.setAttribute("user", person);
            httpSession.setAttribute("userid", person.getId());
            httpSession.setAttribute("logged_in", true);

            return ResponseEntity.ok(person);
        }
    }

    public ResponseEntity<?> logout(HttpSession httpSession){
        httpSession.setAttribute("user", null);
        httpSession.setAttribute("userid", null);
        httpSession.setAttribute("logged_in", false);
        return ResponseEntity.ok(null);
    }

    public String sessionData(HttpSession httpSession){
        if(httpSession.isNew()){
            httpSession.setAttribute("logged_in", false);
        }
        String output = "";
        Enumeration<String> enums = httpSession.getAttributeNames();
        for (Iterator<String> it = enums.asIterator(); it.hasNext(); ) {
            String attrib = it.next();
            output = output +" "+ attrib;
            output = output +" "+ httpSession.getAttribute(attrib).toString() +"\n";
        }
        return output;
    }

    public Person save(Person person){
        if(userRepository.findByLogin(person.getLogin()) != null){
            System.err.println("USER LOGIN ALREADY TAKEN!");
            return null;
        }

        else if(person.getAddress() == null){
            userRepository.save(person);
            person.setAddress(new Address());
        }

        addressService.save(person.getAddress());
        return userRepository.save(person);
    }

    public ResponseEntity<?> getByLogin(String login){
        Person person = userRepository.findByLogin(login);
        if(person == null){
            throw new ApiRequestException("Login not found.");
        }
        return ResponseEntity.ok(userRepository.findByLogin(login));
    }

    public Person getById(Long userId){
        Optional<Person> appUser = userRepository.findById(userId);
        if(appUser.isEmpty()){
            throw new ApiRequestException("Login not found.");
        }
        return appUser.get();
    }

    public List<Person> saveAll(List<Person> personList){
        return userRepository.saveAll(personList);
    }

    public List<Person> getList(){
        return userRepository.findAll();
    }

    public Optional<Person> findById(Long id){
        return userRepository.findById(id);
    }

    public Person get(Long personId) {
        Optional<Person> optional = findById(personId);
        if(optional.isPresent())
            return optional.get();
        throw new ApiRequestException("None found.");
    }
}
