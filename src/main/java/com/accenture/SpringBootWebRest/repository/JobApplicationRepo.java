package com.accenture.SpringBootWebRest.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.SpringBootWebRest.entity.JobApplication;

public interface JobApplicationRepo extends JpaRepository<JobApplication,UUID> {

}
