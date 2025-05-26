package com.accenture.SpringBootWebRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.SpringBootWebRest.model.User;



public interface UserRepo extends JpaRepository<User,Integer>{
	User findByUsername(String username); 

}
