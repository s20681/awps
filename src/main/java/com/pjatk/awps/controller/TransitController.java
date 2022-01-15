package com.pjatk.awps.controller;

import com.pjatk.awps.model.Transit;
import com.pjatk.awps.model.enums.Role;
import com.pjatk.awps.service.TransitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/transit/")
public class TransitController {
    TransitService transitService;

    @Autowired
    public TransitController(TransitService transitService) {
        this.transitService = transitService;
    }

    @GetMapping("new")
    public ResponseEntity<Transit> make(){
        return ResponseEntity.ok(transitService.make());
    }

    @PostMapping("join")
    public ResponseEntity<?> create(HttpSession httpSession, @RequestParam Long transitId, @RequestParam String role){
        return transitService.join(httpSession, transitId, role);
    }

    @PostMapping("leave")
    public ResponseEntity<Transit> leave(HttpSession httpSession, @RequestParam Long transitId){
        return transitService.leave(httpSession, transitId);
    }

    @PostMapping("adduser")
    public ResponseEntity<Transit> addUser(@RequestParam Long transitId, @RequestParam Long personId, @RequestParam String role){
        return transitService.addUser(transitId, personId, role);
    }

    @GetMapping("get")
    public ResponseEntity<Transit> getTransit(@RequestParam Long transitId){
        return ResponseEntity.ok(transitService.findById(transitId));
    }

    @PostMapping("save")
    public Transit set(@RequestBody Transit transit){
        return transitService.save(transit);
    }

    @PostMapping("delete")
    public void delete(@RequestParam Long transitId){
        transitService.deleteById(transitId);
    }

    @GetMapping("all")
    public List<Transit> all(){
        return transitService.getList();
    }
}
