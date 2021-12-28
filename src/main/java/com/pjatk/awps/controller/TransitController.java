package com.pjatk.awps.controller;

import com.pjatk.awps.model.Transit;
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

    @PostMapping("create")
    public ResponseEntity<Transit> create(HttpSession httpSession, @RequestBody Transit transit){
        return transitService.create(httpSession, transit);
    }

    @PostMapping("save")
    public Transit set(@RequestBody Transit transit){
        return transitService.save(transit);
    }

    @GetMapping("all")
    public List<Transit> all(){
        return transitService.getList();
    }
}
