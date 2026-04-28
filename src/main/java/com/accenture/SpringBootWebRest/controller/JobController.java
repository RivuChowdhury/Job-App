package com.accenture.SpringBootWebRest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.SpringBootWebRest.dto.JobPostDTO;
import com.accenture.SpringBootWebRest.service.JobService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

//@Controller
/*If all the methods inside a controller returns data instead of view we can directly write @RestController above the 
Controller. */
@RestController
@Tag(name="Home APIs")
public class JobController {
	@Autowired
	private JobService jobService;	
	
	/*Controller by default expects us to return a View for the ViewResolver to handle.Hence, when we want to send json data
	from the error it will show Internal View Resolver error.To counter this, we need to add @ResponseBody annotation to
	explicitly tell the controller that we are passing json data to handle.If all the methods inside a controller returns data
	instead of view we can directly write @RestController above the Controller. */
	//@ResponseBody
	@GetMapping("jobPosts")
	public ResponseEntity<List<JobPostDTO>> getAlljobs() {
		List<JobPostDTO> jobPostDto=jobService.getAllJobs();
		return ResponseEntity.status(HttpStatus.OK).body(jobPostDto);
	}
	
	@GetMapping("jobPost/{postId}")
	@Operation(summary="Get all the jobs from the database")
	public ResponseEntity<JobPostDTO> getjobById(@PathVariable("postId") int postId) {
		JobPostDTO jobPostDto=jobService.getjobById(postId);
		return ResponseEntity.status(HttpStatus.OK).body(jobPostDto);
	}
	
	@PostMapping("jobPost")
	@Operation(summary="Add jobs in the database")
	public ResponseEntity<String> addJob(@RequestBody JobPostDTO jobPostDto){
		jobService.addJob(jobPostDto);
		return ResponseEntity.ok("Job post created successfully");
	}
	
	@PutMapping("jobPost/{postId}")
	public ResponseEntity<JobPostDTO> updateJob(@RequestBody JobPostDTO jobPostDto,@PathVariable("postId") int userId) {
		JobPostDTO updatedJobPostDto=jobService.updateJob(jobPostDto);
		return ResponseEntity.status(HttpStatus.OK).body(updatedJobPostDto);
	}
	
	@DeleteMapping("jobPost/{postId}")
	public ResponseEntity<String> deleteJob(@PathVariable("postId") int postId) {
		jobService.deleteJob(postId);
		return ResponseEntity.ok("Job deleted successfully");
	}
	
	@PostMapping("load")
	public String loadData() {
		jobService.load();
		return "success";
	}
	
	@GetMapping("jobPost/keyword/{keyword}")
	public List<JobPostDTO> searchByKeyword(@PathVariable("keyword") String keyword){
		return jobService.search(keyword);
	}

}
