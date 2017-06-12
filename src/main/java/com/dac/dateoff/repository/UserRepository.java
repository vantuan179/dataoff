package com.dac.dateoff.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dac.dateoff.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);

}
