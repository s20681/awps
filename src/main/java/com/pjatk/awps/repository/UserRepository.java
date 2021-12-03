package com.pjatk.awps.repository;

import com.pjatk.awps.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByLogin(String login);
    AppUser findByLoginAndPassword(String login, String password);
}
