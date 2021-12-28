package com.pjatk.awps.controller;

import com.pjatk.awps.model.Address;
import com.pjatk.awps.model.Destination;
import com.pjatk.awps.service.AddressService;
import com.pjatk.awps.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/address/")
public class AddressController {
    AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("create")
    public ResponseEntity<Address> create(HttpSession httpSession, @RequestBody Address address){
        return addressService.create(httpSession, address);
    }

    @PostMapping("save")
    public Address set(@RequestBody Address address){
        return addressService.save(address);
    }

    @GetMapping("all")
    public List<Address> all(){
        return addressService.getList();
    }
}
