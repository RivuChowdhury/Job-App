package com.accenture.SpringBootWebRest.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.accenture.SpringBootWebRest.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,UUID>{
	User findByUsername(String username);
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
	
	@Query("SELECT u.username FROM User u WHERE u.username=:userName OR u.username LIKE CONCAT(:userName, '.%') ")
	List<String> findSimilarUsernames(@Param("userName") String userName);

}
