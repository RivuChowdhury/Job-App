package com.accenture.SpringBootWebRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.SpringBootWebRest.entity.User;

public interface UserRepo extends JpaRepository<User,Integer>{
	User findByUsername(String username);
	boolean existsByUsername(String username);

}
