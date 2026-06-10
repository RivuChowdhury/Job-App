package com.accenture.SpringBootWebRest.repository;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.SpringBootWebRest.entity.JobPost;

@Repository
public interface JobPostRepo extends JpaRepository<JobPost,UUID> {
    List<JobPost> findByTitleContainingOrDescriptionContaining(String title,String description);
}






















/*
    
    public List<JobPost> getAllJobs(){
    	return jobs;
    }
    
    public void addJob(JobPost job) {
    	jobs.add(job);
    	System.out.println(jobs);
    }
    
    public JobPost getjob(int postId) {
    	for(JobPost job:jobs) {
    		if(job.getPostid()==postId) {
    			return job;
    		}
    	}
    	return null;
    }
    
    public void updateJob(JobPost jobPost) {
    	for(JobPost job:jobs) {
    		if(jobPost.getPostid()==job.getPostid()) {
    			job.setPostProfile(jobPost.getPostProfile());
    			job.setPostDesc(jobPost.getPostDesc());
    			job.setReqExperience(jobPost.getReqExperience());
    			job.setPostTechStack(jobPost.getPostTechStack());
    		}
    	}
    }
    
    public void deleteJob(int postId) {
    	for(JobPost job:jobs) {
    		if(job.getPostid()==postId) {
    			jobs.remove(job);
    		}
    	}
    }*/