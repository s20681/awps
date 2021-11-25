package com.pjatk.awps.repository;

import com.pjatk.awps.model.PersonalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonalData, Long> {
}
