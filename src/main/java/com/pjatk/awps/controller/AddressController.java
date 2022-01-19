package com.pjatk.awps.controller;

import com.pjatk.awps.model.Address;
import com.pjatk.awps.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/address/")
public class AddressController {
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public Address get(@RequestParam Long addressId){
        return addressService.get(addressId);
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
