package com.accenture.SpringBootWebRest.dto;

import com.accenture.SpringBootWebRest.entity.enums.JobApplicationStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public class JobApplicationDTO {
	
	@NotBlank
	@JsonProperty(value="job_application_status")
	private JobApplicationStatus jobApplicationStatus;
	
	@JsonProperty(value="cover_letter")
	private String coverLetter;

	public JobApplicationStatus getJobApplicationStatus() {
		return jobApplicationStatus;
	}

	public void setJobApplicationStatus(JobApplicationStatus jobApplicationStatus) {
		this.jobApplicationStatus = jobApplicationStatus;
	}

	public String getCoverLetter() {
		return coverLetter;
	}

	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}
	

}
