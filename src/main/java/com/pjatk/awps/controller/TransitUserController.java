package com.pjatk.awps.controller;

import com.pjatk.awps.model.TransitUser;
import com.pjatk.awps.service.TransitUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transituser/")
public class TransitUserController {
    TransitUserService transitUserService;

    @Autowired
    public TransitUserController(TransitUserService transitUserService) {
        this.transitUserService = transitUserService;
    }

    @PostMapping("save")
    public TransitUser set(@RequestBody TransitUser transitUser){
        return transitUserService.save(transitUser);
    }

    @DeleteMapping("delete")
    public void delete(@RequestParam Long transitUserId){
        transitUserService.delete(transitUserId);
    }

    @GetMapping("all")
    public List<TransitUser> all(){
        return transitUserService.findAll();
    }
}
