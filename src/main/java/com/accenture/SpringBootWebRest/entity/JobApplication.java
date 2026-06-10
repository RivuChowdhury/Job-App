package com.accenture.SpringBootWebRest.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.accenture.SpringBootWebRest.entity.enums.JobApplicationStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="job_application")
public class JobApplication {
	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	private UUID jobApplicationId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="job_post_id",referencedColumnName="job_post_id",nullable=false)
	private JobPost jobPost;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="profile_id",referencedColumnName="user_profile_id",nullable=false)
	private UserProfile userProfile;
	
	@Column(nullable=false,name="job_application_status")
	@Enumerated(EnumType.STRING)
	private JobApplicationStatus jobApplicationStatus;
	
	@Column(name="cover_letter")
	private String coverLetter;
	
	@Column(nullable=false,name="applied_at")
	private LocalDateTime appliedAt;
	
	@Column(nullable=false,name="last_updated_at")
	private LocalDateTime lastUpdatedAt;

	public UUID getJobApplicationId() {
		return jobApplicationId;
	}

	public void setJobApplicationId(UUID jobApplicationId) {
		this.jobApplicationId = jobApplicationId;
	}

	public JobPost getJobPost() {
		return jobPost;
	}

	public void setJobPost(JobPost jobPost) {
		this.jobPost = jobPost;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

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

	public LocalDateTime getAppliedAt() {
		return appliedAt;
	}

	public void setAppliedAt(LocalDateTime appliedAt) {
		this.appliedAt = appliedAt;
	}

	public LocalDateTime getLastUpdatedAt() {
		return lastUpdatedAt;
	}

	public void setLastUpdatedAt(LocalDateTime lastUpdatedAt) {
		this.lastUpdatedAt = lastUpdatedAt;
	}
	
	

}
