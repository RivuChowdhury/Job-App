package com.accenture.SpringBootWebRest.entity;

import java.util.List;



import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Positive;

@Entity
public class JobPost {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;
	@Column(nullable=false)
	private String postProfile;
	@Column(nullable=false)
	private String postDesc;
	@Positive
	private int reqExperience;
	@ElementCollection
	private List<String> postTechStack;
	
	public JobPost() {}
	
	public JobPost(int postId, String postProfile, String postDesc, int reqExperience, List<String> postTechStack) {
		super();		
		this.postProfile = postProfile;
		this.postDesc = postDesc;
		this.reqExperience = reqExperience;
		this.postTechStack = postTechStack;
		//this.postId = postId;
	}
	public int getPostId() {
	    return postId;
	}
	public void setPostId(int postId) {
	    this.postId = postId;
	}

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
