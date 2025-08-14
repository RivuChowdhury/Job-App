package com.accenture.SpringBootWebRest.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.accenture.SpringBootWebRest.model.JobPost;
import com.accenture.SpringBootWebRest.repository.JobRepo;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class JobServiceTest {
	
	@Test
	public void firstTest() {
		System.out.println("Inside first test");
	}
	
	@Mock
	private JobRepo jobRepo;
	
	@InjectMocks
	private JobService jobService;
	
	//static JobPost jobPost=null;
	
	private JobPost jobPost;
	@BeforeEach
	public void init() {
		jobPost=new JobPost();
		jobPost.setPostId(1);
		jobPost.setPostProfile("Rivu");
		jobPost.setPostDesc("SpringBoot Developer");
		jobPost.setPostTechStack(Arrays.asList("Java", "MYSQL", "MongoDB", "Spring", "Hibernate"));
		jobPost.setReqExperience(2);
	}
	
	@Test
	void addJobSuccessfully() {
				
		Mockito.when(jobRepo.save(jobPost)).thenReturn(jobPost);
		jobService.addJob(jobPost);
		
		Assertions.assertEquals(1,jobPost.getPostId());
		
	}
	
	@Test 
	void getAllJobsSuccessfully() {
		List<JobPost> jobList = new ArrayList<>();

		jobList.add(jobPost);
		
		Mockito.when(jobRepo.findAll()).thenReturn(jobList);
		List<JobPost> addedJobList=jobService.getAllJobs();
		
		Assertions.assertEquals(1, addedJobList.size());
		Assertions.assertEquals("Rivu",addedJobList.get(0).getPostProfile());
	}
	
	@Test 
	void getJobSuccessfully() {
		
		Mockito.when(jobRepo.findById(jobPost.getPostId())).thenReturn(Optional.of(jobPost));
	    Optional<JobPost> fetchJob=jobService.getjob(jobPost.getPostId());
	    
	    
		Assertions.assertTrue(fetchJob.isPresent());
	    Assertions.assertEquals(jobPost.getPostProfile(),fetchJob.get().getPostProfile());
		
	}
	
	@Test
	void updateJobSuccessfully() {
		jobPost.setPostProfile("Ramkanta");
		Mockito.when(jobRepo.save(jobPost)).thenReturn(jobPost);
		jobService.updateJob(jobPost);
		Assertions.assertEquals("Ramkanta",jobPost.getPostProfile());
	}
	
	@Test
	void deleteJobSuccessfully() {
		doNothing().when(jobRepo).deleteById(1);
		jobService.deleteJob(1);
		
		Mockito.verify(jobRepo,times(1)).deleteById(1);
	}

}
