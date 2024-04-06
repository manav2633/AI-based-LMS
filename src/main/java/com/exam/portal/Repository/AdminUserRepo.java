package com.exam.portal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exam.portal.Model.AdminUser;

public interface AdminUserRepo extends JpaRepository<AdminUser, Integer> {

	@Query(value="SELECT * FROM adminuser WHERE email=:username", nativeQuery=true)
	AdminUser findByEmail(@Param("username")String username);

}
