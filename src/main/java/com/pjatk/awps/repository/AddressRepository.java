package com.pjatk.awps.repository;

import com.pjatk.awps.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAllByPostalCodeAndStreetAndStreetNo(String postalCode, String street, Integer streetNo);
}
