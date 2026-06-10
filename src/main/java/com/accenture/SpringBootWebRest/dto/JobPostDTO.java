package com.accenture.SpringBootWebRest.dto;

import java.time.LocalDateTime;
import java.util.List;
import com.accenture.SpringBootWebRest.entity.enums.EmployeementType;
import com.accenture.SpringBootWebRest.entity.enums.JobPostStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class JobPostDTO {

	@NotBlank
	@JsonProperty(value="title")
	private String title;
	
	@JsonProperty(value="description")
	private String description;
	
	@NotBlank
	@JsonProperty(value="location")
	private String location;
	
	@NotNull
	@JsonProperty("employment_type")
	private EmployeementType employeementType;
	
	@NotBlank
	@JsonProperty(value="domain")
	private String domain;
	
	@Positive
	@NotBlank
	@JsonProperty(value="required_experience")
	private int reqExperience;
	
	@NotBlank
	@JsonProperty(value="required_skills")
	private List<String> reqSkills;
	
	@NotBlank
	@Positive
	@JsonProperty(value="min_salary")
	private int minSalary;
	
	@NotBlank
	@Positive
	@JsonProperty(value="max_salary")
	private int maxSalary;
	
	@NotNull
	@JsonProperty("job_post_status")
	private JobPostStatus jobPostStatus;
		
	@NotBlank
	@JsonProperty(value="deadline")
	private LocalDateTime deadline;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public EmployeementType getEmployeementType() {
		return employeementType;
	}

	public void setEmployeementType(EmployeementType employeementType) {
		this.employeementType = employeementType;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public int getReqExperience() {
		return reqExperience;
	}

	public void setReqExperience(int reqExperience) {
		this.reqExperience = reqExperience;
	}

	public List<String> getReqSkills() {
		return reqSkills;
	}

	public void setReqSkills(List<String> reqSkills) {
		this.reqSkills = reqSkills;
	}

	public int getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(int minSalary) {
		this.minSalary = minSalary;
	}

	public int getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(int maxSalary) {
		this.maxSalary = maxSalary;
	}

	public JobPostStatus getJobPostStatus() {
		return jobPostStatus;
	}

	public void setJobPostStatus(JobPostStatus jobPostStatus) {
		this.jobPostStatus = jobPostStatus;
	}

	public LocalDateTime getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}
	

}
