package com.accenture.SpringBootWebRest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;



public class RecruiterDTO {	
	@JsonProperty("company_name")
	private String companyName;
	
	@JsonProperty("company_website")
	private String companyWebsite;

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
