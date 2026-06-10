package com.accenture.SpringBootWebRest.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.SpringBootWebRest.entity.Recruiter;

public interface RecruiterRepo extends JpaRepository<Recruiter,UUID> {

}
