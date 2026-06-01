package com.accenture.SpringBootWebRest.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.SpringBootWebRest.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,UUID>{
	User findByUsername(String username);
	boolean existsByUsername(String username);

}
