package com.pjatk.awps.repository;

import com.pjatk.awps.model.AppUser;
import com.pjatk.awps.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
