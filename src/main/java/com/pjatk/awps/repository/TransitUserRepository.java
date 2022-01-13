package com.pjatk.awps.repository;

import com.pjatk.awps.model.TransitUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransitUserRepository extends JpaRepository<TransitUser, Long> {
}
