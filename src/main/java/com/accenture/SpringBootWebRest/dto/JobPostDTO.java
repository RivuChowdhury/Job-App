package com.accenture.SpringBootWebRest.dto;

import java.util.List;



import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class JobPostDTO {
	
	@NotBlank
	@JsonProperty("post_profile")
	private String postProfile;
	
	@NotBlank
	@JsonProperty("post_desc")
	private String postDesc;
	
	@Positive
	@JsonProperty("req_experience")
	private int reqExperience;
	
	@JsonProperty("post_tech_stack")
	private List<String> postTechStack;
	
	public String getPostProfile() {
		return postProfile;
	}
	public void setPostProfile(String postProfile) {
		this.postProfile = postProfile;
	}
	public String getPostDesc() {
		return postDesc;
	}
	public void setPostDesc(String postDesc) {
		this.postDesc = postDesc;
	}
	public int getReqExperience() {
		return reqExperience;
	}
	public void setReqExperience(int reqExperience) {
		this.reqExperience = reqExperience;
	}
	public List<String> getPostTechStack() {
		return postTechStack;
	}
	public void setPostTechStack(List<String> postTechStack) {
		this.postTechStack = postTechStack;
	}
	

}
