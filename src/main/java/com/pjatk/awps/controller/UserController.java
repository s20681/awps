package com.pjatk.awps.controller;

import com.pjatk.awps.model.AppUser;
import com.pjatk.awps.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
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