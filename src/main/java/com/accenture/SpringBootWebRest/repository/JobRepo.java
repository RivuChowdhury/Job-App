package com.accenture.SpringBootWebRest.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.SpringBootWebRest.model.JobPost;


@Repository
public interface JobRepo extends JpaRepository<JobPost,Integer> {
    List<JobPost> findByPostProfileContainingOrPostDescContaining(String postProfile,String postDesc);
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