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
import org.modelmapper.ModelMapper;

import com.accenture.SpringBootWebRest.dto.JobPostDTO;
import com.accenture.SpringBootWebRest.entity.JobPost;
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
	
	@Mock
	private ModelMapper modelMapper;
	
	@InjectMocks
	private JobService jobService;
	
	//static JobPost jobPost=null;
	
	private JobPost jobPost;
	private JobPostDTO jobPostDto;
	@BeforeEach
	public void init() {
		jobPost=new JobPost();
		jobPost.setPostId(1);
		jobPost.setPostProfile("Spring Boot Developer");
		jobPost.setPostDesc("Experince in SpringBoot, Spring MVC, Java");
		jobPost.setPostTechStack(Arrays.asList("Java", "MYSQL", "MongoDB", "Spring", "Hibernate"));
		jobPost.setReqExperience(2);
		
		jobPostDto = new JobPostDTO();
	    jobPostDto.setPostProfile("Spring Boot Developer");
	    jobPostDto.setPostDesc("Experience in SpringBoot, Spring MVC, Java");
	    jobPostDto.setPostTechStack(Arrays.asList("Java", "MYSQL", "MongoDB", "Spring", "Hibernate"));
	    jobPostDto.setReqExperience(2);
	}
	
	//NOTE: The comments above the coding lines of Mockito.when() are the actual implementation of the actual code which we are trying to mock.
	@Test
	void addJobSuccessfully() {
		//JobPost jobPost=modelMapper.map(jobPostDTO, JobPost.class);
		Mockito.when(modelMapper.map(jobPostDto, JobPost.class)).thenReturn(jobPost);
		
		//jobRepo.save(jobPost);
		Mockito.when(jobRepo.save(jobPost)).thenReturn(jobPost);
		
		//return modelMapper.map(jobPost,JobPostDTO.class);
		Mockito.when(modelMapper.map(jobPost, JobPostDTO.class)).thenReturn(jobPostDto);

		//Here, result is just jobPostDto which got returned from the code line just above this comment while converting the Entity to DTO.
		JobPostDTO result=jobService.addJob(jobPostDto);
		
		Assertions.assertEquals("Spring Boot Developer",result.getPostProfile());	
	}
	
	@Test 
	void getAllJobsSuccessfully() {
		List<JobPost> jobList = new ArrayList<>();
		jobList.add(jobPost);
		
		JobPostDTO[] jobPostDtoArr = new JobPostDTO[]{jobPostDto};
		
		//List<JobPost> jobPost=jobRepo.findAll();
		Mockito.when(jobRepo.findAll()).thenReturn(jobList);
		
		//JobPostDTO[] jobPostModelMap=modelMapper.map(jobPost, JobPostDTO[].class);
		Mockito.when(modelMapper.map(jobList, JobPostDTO[].class)).thenReturn(jobPostDtoArr);
		
		/*NOTE: This line List<JobPostDTO> allJobPostDto=Arrays.asList(jobPostModelMap); in JobService is not mocked because it is not an external 
		dependency instead Array.asList() is a Core Java method and will run successfully every time. */
		List<JobPostDTO> result=jobService.getAllJobs();
		
		Assertions.assertEquals(1, result.size());
		Assertions.assertEquals("Spring Boot Developer",result.get(0).getPostProfile());
	}
	
	@Test
	void getJobSuccessfully() {

	    Mockito.when(jobRepo.findById(1)).thenReturn(Optional.of(jobPost));
	    Mockito.when(modelMapper.map(jobPost, JobPostDTO.class)).thenReturn(jobPostDto);

	    Optional<JobPostDTO> result = jobService.getjobById(1);

	    Assertions.assertTrue(result.isPresent());
	    Assertions.assertEquals("Spring Boot Developer", result.get().getPostProfile());
	}
	
	@Test
	void updateJobSuccessfully() throws Exception {
		jobPostDto.setPostProfile("Ramkanta");
		
		Mockito.when(jobRepo.findById(1)).thenReturn(Optional.of(jobPost));
        Mockito.when(modelMapper.map(jobPostDto, JobPost.class)).thenReturn(jobPost);
		Mockito.when(jobRepo.save(jobPost)).thenReturn(jobPost);
		Mockito.when(modelMapper.map(jobPost, JobPostDTO.class)).thenReturn(jobPostDto);
		
		JobPostDTO result=jobService.updateJob(jobPostDto,jobPost.getPostId());
		Assertions.assertEquals("Ramkanta", result.getPostProfile());
	}
	
	@Test
	void deleteJobSuccessfully() {
		doNothing().when(jobRepo).deleteById(1);
		jobService.deleteJob(1);
		
		Mockito.verify(jobRepo,times(1)).deleteById(1);
	}

}
