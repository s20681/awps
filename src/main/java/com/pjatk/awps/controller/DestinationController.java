package com.pjatk.awps.controller;

import com.pjatk.awps.model.Destination;
import com.pjatk.awps.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/destination/")
public class DestinationController {
    DestinationService destinationService;

    @Autowired
    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @PostMapping("create")
    public ResponseEntity<Destination> create(HttpSession httpSession, @RequestBody Destination destination){
        return destinationService.create(httpSession, destination);
    }

    @PostMapping("save")
    public Destination set(@RequestBody Destination destination){
        return destinationService.save(destination);
    }

    @GetMapping("all")
    public List<Destination> all(){
        return destinationService.getList();
    }
}
