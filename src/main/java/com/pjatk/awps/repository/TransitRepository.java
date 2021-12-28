package com.pjatk.awps.repository;

import com.pjatk.awps.model.Destination;
import com.pjatk.awps.model.Transit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransitRepository extends JpaRepository<Transit, Long> {
}
