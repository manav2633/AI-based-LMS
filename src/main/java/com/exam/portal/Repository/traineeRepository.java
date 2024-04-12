package com.exam.portal.Repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.portal.Model.trainee;

public interface traineeRepository extends JpaRepository<trainee , Integer> {

    @Query(value = "select * from trainee where email=?1")
    trainee findByEmail(String email);
    
}
