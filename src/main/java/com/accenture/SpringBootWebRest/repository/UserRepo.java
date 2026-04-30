package com.accenture.SpringBootWebRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.SpringBootWebRest.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,Integer>{
	User findByUsername(String username);
	boolean existsByUsername(String username);

}
