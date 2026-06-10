package com.accenture.SpringBootWebRest.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.SpringBootWebRest.entity.UserProfile;

public interface UserProfileRepo extends JpaRepository<UserProfile,UUID> {

}
