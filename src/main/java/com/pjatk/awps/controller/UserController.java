package com.pjatk.awps.controller;

import com.pjatk.awps.model.AppUser;
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
    public ResponseEntity<?> login(HttpSession httpSession, @RequestBody AppUser appUser){
        return userService.login(httpSession, appUser);
    }

    @PostMapping("logout")
    public ResponseEntity<?> logout(HttpSession httpSession){
        return userService.logout(httpSession);
    }

    @PostMapping("set")
    public AppUser set(@RequestBody AppUser appUser){
        return userService.save(appUser);
    }

    @GetMapping("get")
    public AppUser get(){
        return userService.getSample();
    }

    @GetMapping("getlist")
    public List<AppUser> getList(){
        return userService.getList();
    }

}