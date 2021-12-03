package com.pjatk.awps.repository;

import com.pjatk.awps.model.Destination;
import com.pjatk.awps.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {
}