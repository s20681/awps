package com.pjatk.awps.service;

import com.pjatk.awps.model.Address;
import com.pjatk.awps.model.Transit;
import com.pjatk.awps.model.TransitAddress;
import com.pjatk.awps.repository.TransitAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransitAddressService {
    private final TransitAddressRepository transitAddressRepository;

    @Autowired
    public TransitAddressService(TransitAddressRepository transitAddressRepository) {
        this.transitAddressRepository = transitAddressRepository;
    }

    public TransitAddress make(Transit transit, Address address){
        TransitAddress transitAddress = new TransitAddress();
        transitAddress.setAddress(address);
        transitAddress.setTransit(transit);
        return save(transitAddress);
    }

    public TransitAddress save(TransitAddress transitAddress) {
        return transitAddressRepository.save(transitAddress);
    }

    public void delete(Long id) {
        transitAddressRepository.deleteById(id);
    }

    public List<TransitAddress> findAll() {
        return transitAddressRepository.findAll();
    }

    public List<TransitAddress> addressesByTransit(Long transitId) {

        List<TransitAddress> transitAddresses = new ArrayList<>();
        for (TransitAddress t :
                findAll()) {
            if(t.getTransitId().equals(transitId)){
                transitAddresses.add(t);
            }
        }

        return transitAddresses;
    }

    public Optional<TransitAddress> findById(Long addressId) {
        return transitAddressRepository.findById(addressId);
    }
}
