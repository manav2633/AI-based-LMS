package com.exam.portal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.portal.Model.trainee;

public interface traineeRepository extends JpaRepository<trainee , Integer> {
    
}
