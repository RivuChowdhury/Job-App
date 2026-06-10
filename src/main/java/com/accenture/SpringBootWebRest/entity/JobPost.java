package com.accenture.SpringBootWebRest.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.accenture.SpringBootWebRest.entity.enums.EmployeementType;
import com.accenture.SpringBootWebRest.entity.enums.JobPostStatus;

import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name="job_post")
public class JobPost {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name="job_post_id")
	private UUID jobPostId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="recruiter_id",referencedColumnName="recruiter_id",nullable=false) /*Here, name="recruiter_id" represents foreign key in 
	                                                                                    job_post table and the referencedColumnName="recruiter_id" 
	                                                                                    represents primary key in recruiter table. */
	private Recruiter recruiter;

	@Column(nullable=false)
	private String title;
	
	@Column(columnDefinition="TEXT")
	private String description;
	
	@Column(nullable=false)
	private String location;
	
	@Column(nullable=false,name="employment_type")
	@Enumerated(EnumType.STRING)
	private EmployeementType employeementType;
	
	@Column(nullable=false)
	private String domain;
	
	@Positive
	@Column(nullable=false,name="required_experience")
	private int reqExperience;
	
	@JdbcTypeCode(SqlTypes.JSON)
	@Column(nullable=false,name="required_skills",columnDefinition="jsonb")
	private List<String> reqSkills;
	
	@Positive
	@Column(nullable=false,name="min_salary")
	private int minSalary;
	
	@Positive
	@Column(nullable=false,name="max_salary")
	private int maxSalary;
	
	@Column(nullable=false,name="job_post_status")
	@Enumerated(EnumType.STRING)
	private JobPostStatus jobPostStatus;
	
	@Column(nullable=false,name="job_posted_at")
	private LocalDateTime jobPostedAt;
	
	@Column(nullable=false)
	private LocalDateTime deadline;
	
	@OneToMany(mappedBy="jobPost",cascade=CascadeType.ALL, orphanRemoval=true)
	private List<JobApplication> jobApplications;
	
	public List<JobApplication> getJobApplications() {
		return jobApplications;
	}

	public void setJobApplications(List<JobApplication> jobApplications) {
		this.jobApplications = jobApplications;
	}

	public UUID getJobPostId() {
		return jobPostId;
	}

	public void setJobPostId(UUID jobPostId) {
		this.jobPostId = jobPostId;
	}

	public Recruiter getRecruiter() {
		return recruiter;
	}

	public void setRecruiter(Recruiter recruiter) {
		this.recruiter = recruiter;
	}

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

	public LocalDateTime getJobPostedAt() {
		return jobPostedAt;
	}

	public void setJobPostedAt(LocalDateTime jobPostedAt) {
		this.jobPostedAt = jobPostedAt;
	}

	public LocalDateTime getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}
		

}
