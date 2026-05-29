package com.accenture.SpringBootWebRest.dto;



import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

@Component
public class AuthRequestDTO {
	
	@NotBlank
	@JsonProperty("user_name")
	private String username;
	@NotBlank
	@JsonProperty(value="password",access=JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
