package com.pjatk.awps.repository;

import com.pjatk.awps.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Person, Long> {
    Person findByLogin(String login);
    Person findByLoginAndPassword(String login, String password);
}
