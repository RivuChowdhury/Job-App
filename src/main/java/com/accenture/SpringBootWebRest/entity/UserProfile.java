package com.accenture.SpringBootWebRest.entity;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="user_profiles")
public class UserProfile {
	@Id
	@Column(name="user_profile_id")
	private UUID profileId;
	
	@OneToOne
	@MapsId    // Derives PK of this UserProfile table from User.profileId in the User table
	@JoinColumn(name="user_profile_id",referencedColumnName="profile_id")  /*Here, profile_id refers to the same profileId in User table(name is 
	                                                                       converted from camel case to snake case automatically by hibernate).*/
	private User user;
	
	private String domain;
	
	private int experience;
	
	
	@JdbcTypeCode(SqlTypes.JSON)  /* It explicitly tells the Hibernate to serialized this column data as Json while reading or writing it.*/
	@Column(name="skills",columnDefinition="jsonb") /* columnDefinition="jsonb" tells the DB to create/expect this column as PostgreSQL's jsonb type 
	                                                   instead of a default varchar */
	private List<String> skills;
	
	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name="education",columnDefinition="jsonb")
	private List<String> education;
	
	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name="social_links",columnDefinition="jsonb")
	private List<String> socialLinks;
	
	@Column(name="profile_image_url")
	private String profileImageURL;
	
	@Column(name="resume_url")
	private String resumeURL;
		
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UUID getProfileId() {
		return profileId;
	}
	public void setProfileId(UUID profileId) {
		this.profileId = profileId;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public List<String> getSkills() {
		return skills;
	}
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	public List<String> getEducation() {
		return education;
	}
	public void setEducation(List<String> education) {
		this.education = education;
	}
	public List<String> getSocialLinks() {
		return socialLinks;
	}
	public void setSocialLinks(List<String> socialLinks) {
		this.socialLinks = socialLinks;
	}
	public String getProfileImageURL() {
		return profileImageURL;
	}
	public void setProfileImageURL(String profileImageURL) {
		this.profileImageURL = profileImageURL;
	}
	public String getResumeURL() {
		return resumeURL;
	}
	public void setResumeURL(String resumeURL) {
		this.resumeURL = resumeURL;
	}
	
	


}
