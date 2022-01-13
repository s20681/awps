package com.pjatk.awps.repository;

import com.pjatk.awps.model.TransitAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransitAddressRepository extends JpaRepository<TransitAddress, Long> {
}
