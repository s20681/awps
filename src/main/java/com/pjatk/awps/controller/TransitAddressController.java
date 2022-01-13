package com.pjatk.awps.controller;

import com.pjatk.awps.model.TransitAddress;
import com.pjatk.awps.service.TransitAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transitaddress/")
public class TransitAddressController {
    private final TransitAddressService transitAddressService;

    @Autowired
    public TransitAddressController(TransitAddressService transitAddressService) {
        this.transitAddressService = transitAddressService;
    }

    @GetMapping("bytransit")
    public List<TransitAddress> addressesByTransit(@RequestParam Long transitId){
        return transitAddressService.addressesByTransit(transitId);
    }

    @GetMapping("all")
    public List<TransitAddress> all(){
        return transitAddressService.findAll();
    }

    @PostMapping("save")
    public TransitAddress set(@RequestBody TransitAddress transitAddress){
        return transitAddressService.save(transitAddress);
    }

    @DeleteMapping("delete")
    public void delete(@RequestParam Long id){
        transitAddressService.delete(id);
    }


}
