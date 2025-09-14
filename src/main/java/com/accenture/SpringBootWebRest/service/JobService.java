package com.accenture.SpringBootWebRest.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.SpringBootWebRest.model.JobPost;
import com.accenture.SpringBootWebRest.repository.JobRepo;


@Service
public class JobService {
	
	@Autowired
	private JobRepo jobRepo;
	
	//private static final Logger logger=LoggerFactory.getLogger(JobService.class);
	
	public void addJob(JobPost jobPost) {
		System.out.println("Saving job: " + jobPost);
		jobRepo.save(jobPost);		
	}
	
	public List<JobPost> getAllJobs(){
		return jobRepo.findAll();
	}
	
	public Optional<JobPost> getjob(int postId) {
		//int num=10/0;
		return jobRepo.findById(postId);
	}
	
	public void updateJob(JobPost jobPost) {
		System.out.println("Updating job: " + jobPost);
		jobRepo.save(jobPost);
	}
	
	public void deleteJob(int postId) {
		jobRepo.deleteById(postId);
	}

	public void load() {
		List<JobPost> jobs = new ArrayList<>(Arrays.asList(
		        new JobPost(1, "Java Developer", "Must have good experience in core Java and advanced Java", 2,
		            Arrays.asList("Core Java", "J2EE", "Spring Boot", "Hibernate")),

		        new JobPost(2, "Frontend Developer", "Experience in building responsive web applications using React", 3,
		            Arrays.asList("HTML", "CSS", "JavaScript", "React")),

		        new JobPost(3, "Data Scientist", "Strong background in machine learning and data analysis", 4,
		            Arrays.asList("Python", "Machine Learning", "Data Analysis")),

		        new JobPost(4, "Network Engineer", "Design and implement computer networks for efficient data communication", 5,
		            Arrays.asList("Networking", "Cisco", "Routing", "Switching")),

		        new JobPost(5, "Mobile App Developer", "Experience in mobile app development for iOS and Android", 3,
		            Arrays.asList("iOS Development", "Android Development", "Mobile App"))
		    ));
		jobRepo.saveAll(jobs);
		
	}

	public List<JobPost> search(String keyword) {
		return jobRepo.findByPostProfileContainingOrPostDescContaining(keyword, keyword);
	}

}
