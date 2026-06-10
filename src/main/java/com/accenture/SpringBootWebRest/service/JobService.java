package com.accenture.SpringBootWebRest.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.SpringBootWebRest.dto.JobPostDTO;
import com.accenture.SpringBootWebRest.entity.JobPost;
import com.accenture.SpringBootWebRest.repository.JobPostRepo;




@Service
public class JobService {
	
	@Autowired
	private JobPostRepo jobPostRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//private static final Logger logger=LoggerFactory.getLogger(JobService.class);
	
	public JobPostDTO addJob(JobPostDTO jobPostDTO) {
		JobPost jobPost=modelMapper.map(jobPostDTO, JobPost.class);
		jobPostRepo.save(jobPost);		
		return modelMapper.map(jobPost,JobPostDTO.class);
	}
	
	public List<JobPostDTO> getAllJobs(){
		List<JobPost> jobPost=jobPostRepo.findAll();
		JobPostDTO[] jobPostModelMap=modelMapper.map(jobPost, JobPostDTO[].class);
		List<JobPostDTO> allJobPostDto=Arrays.asList(jobPostModelMap);
		return allJobPostDto;
	}
	
	public Optional<JobPostDTO> getjobById(UUID postId) {
	    Optional<JobPost> jobPost = jobPostRepo.findById(postId);
	    if (jobPost.isPresent()) {
	        JobPostDTO jobPostDto = modelMapper.map(jobPost.get(), JobPostDTO.class);
	        return Optional.of(jobPostDto);
	    }
	    return Optional.empty();
	}
	
	public JobPostDTO updateJob(JobPostDTO jobPostDTO,UUID postId) throws Exception {
		JobPost existingJobPost=jobPostRepo.findById(postId).orElseThrow(() -> new RuntimeException("Job Post Not found"));
		JobPost jobPost=modelMapper.map(jobPostDTO, JobPost.class);
		JobPost savedJobPost=jobPostRepo.save(jobPost);
		return modelMapper.map(savedJobPost, JobPostDTO.class);

	}
	
	public void deleteJob(UUID postId) {
		jobPostRepo.deleteById(postId);
	}
	
	public List<JobPostDTO> search(String keyword) {
		List<JobPost> foundSimilarJobPost=jobPostRepo.findByTitleContainingOrDescriptionContaining(keyword, keyword);
		JobPostDTO[] jobPostModelMap=modelMapper.map(foundSimilarJobPost,JobPostDTO[].class); 
		List<JobPostDTO> foundSimilarJobPostDto=Arrays.asList(jobPostModelMap);
		return foundSimilarJobPostDto;
	}
	

}
