package com.pjatk.awps.controller;

import com.pjatk.awps.model.Destination;
import com.pjatk.awps.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

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
}
