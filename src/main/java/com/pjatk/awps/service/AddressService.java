package com.pjatk.awps.service;

import com.pjatk.awps.exception.ApiRequestException;
import com.pjatk.awps.model.Address;
import com.pjatk.awps.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address save(Address address){
        if(address == null){
            address = new Address();
        }
        return addressRepository.save(address);
    }


    public List<Address> getList() {
        return addressRepository.findAll();
    }

    public ResponseEntity<List<Address>> find(HttpSession httpSession, Address address){
        if(httpSession.getAttribute("user") == null){
            throw new ApiRequestException("You must be logged in to check for addresses");
        }
        List<Address> addressesFitting = addressRepository.findAllByPostalCodeAndStreetAndStreetNo(
                address.getCity(),
                address.getStreet(),
                address.getStreetNo()
        );

        if(addressesFitting.isEmpty()){
            return null;
        }

        return ResponseEntity.ok(addressesFitting);
    }
}
