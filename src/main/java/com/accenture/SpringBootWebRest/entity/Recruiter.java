package com.accenture.SpringBootWebRest.entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="recruiter")
public class Recruiter {
	@Id
	@Column(name="recruiter_id")
	private UUID recruiterId;
	
	@OneToOne
	@MapsId     // Derives PK of this UserProfile table from User.profileId in the User table
	@JoinColumn(name="recruiter_id",referencedColumnName="profile_id")  /*Here, profile_id refers to the same profileId in User table(name is converted
                                                                         from camel case to snake case automatically by hibernate).*/
	private User user;
	
	@Column(name="company_name")
	private String companyName;
	
	@Column(name="company_website")
	private String companyWebsite;
	
	@OneToMany(mappedBy="recruiter",cascade=CascadeType.ALL, orphanRemoval=true)
	private List<JobPost> jobPosts;
	
	
	public List<JobPost> getJobPosts() {
		return jobPosts;
	}

	public void setJobPosts(List<JobPost> jobPosts) {
		this.jobPosts = jobPosts;
	}

	public UUID getRecruiterId() {
		return recruiterId;
	}

	public void setRecruiterId(UUID recruiterId) {
		this.recruiterId = recruiterId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getCompanyWebsite() {
		return companyWebsite;
	}

	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}


}
