package com.pjatk.awps.controller;

import com.pjatk.awps.model.Person;
import com.pjatk.awps.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/user/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("session")
    public String getSessionData(HttpSession httpSession){
        return userService.sessionData(httpSession);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(HttpSession httpSession, @RequestBody Person person){
        return userService.login(httpSession, person);
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody Person person){
        return userService.register(person);
    }

    @PostMapping("logout")
    public ResponseEntity<?> logout(HttpSession httpSession){
        return userService.logout(httpSession);
    }

    @GetMapping("{login}")
    public ResponseEntity<?> getByLogin(@PathVariable String login){
        return userService.getByLogin(login);
    }

    @PostMapping("set")
    public Person set(@RequestBody Person person){
        return userService.save(person);
    }

    @GetMapping("get")
    public Person get(@RequestParam Long personId){
        return userService.get(personId);
    }

    @GetMapping("getlist")
    public List<Person> getList(){
        return userService.getList();
    }

}