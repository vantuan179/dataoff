package com.dac.dateoff.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dac.dateoff.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	List<Role> findByUsers_UserId(Long userId);
}
