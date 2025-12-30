package com.accenture.SpringBootWebRest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.SpringBootWebRest.model.JobPost;
import com.accenture.SpringBootWebRest.service.JobService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

//@Controller
/*If all the methods inside a controller returns data instead of view we can directly write @RestController above the 
Controller. */
@RestController
@Tag(name="Home APIs")
public class HomeController {
	@Autowired
	private JobService jobService;
	
	@GetMapping("hello")
	public String greet() {
		return "Hello";
	}
	
	@GetMapping("jobPosts")
	/*Controller by default expects us to return a View for the ViewResolver to handle.Hence, when we want to send json data
	from the error it will show Internal View Resolver error.To counter this, we need to add @ResponseBody annotation to
	explicitly tell the controller that we are passing json data to handle.If all the methods inside a controller returns data
	instead of view we can directly write @RestController above the Controller. */
	//@ResponseBody
	public List<JobPost> getAlljobs() {
		return jobService.getAllJobs();
	}
	
	@GetMapping("jobPost/{postId}")
	@Operation(summary="Get all the jobs from the database")
	public Optional<JobPost> getjob(@PathVariable("postId") int postId) {
		return jobService.getjob(postId);
	}
	
	@PostMapping("jobPost")
	@Operation(summary="Add jobs in the database")
	public void addJob(@RequestBody JobPost jobPost){
		jobService.addJob(jobPost);
	}
	
	@PutMapping("jobPost")
	public Optional<JobPost> updateJob(@RequestBody JobPost jobPost) {
		jobService.updateJob(jobPost);
		//This line will just print the changes that we want to confirm that everything is working hence it is not mandatory.
		return jobService.getjob(jobPost.getPostId());
	}
	
	@DeleteMapping("jobPost/{postId}")
	public String deleteJob(@PathVariable("postId") int postId) {
		jobService.deleteJob(postId);
		return "Deleted";		
	}
	
	@PostMapping("load")
	public String loadData() {
		jobService.load();
		return "success";
	}
	
	@GetMapping("jobPost/keyword/{keyword}")
	public List<JobPost> searchByKeyword(@PathVariable("keyword") String keyword){
		return jobService.search(keyword);
	}

}
