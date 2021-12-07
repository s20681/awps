package com.pjatk.awps.repository;

import com.pjatk.awps.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Address, Long> {
}
