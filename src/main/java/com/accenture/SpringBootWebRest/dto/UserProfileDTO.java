package com.accenture.SpringBootWebRest.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;


public class UserProfileDTO {
	
	@JsonProperty("domain")
	private String domain;
	
	@JsonProperty("experience")
	private int experience;
	
	@JsonProperty("skills")
	private List<String> skills;
	
	@JsonProperty("education")
	private List<String> education;
	
	@JsonProperty("social_links")
	private List<String> socialLinks;
	
	@JsonProperty("profile_image_url")
	private String profileImageURL;
	
	@JsonProperty("resume_url")
	private String resumeURL;

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
