package com.accenture.SpringBootWebRest.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO {	

	//This @NotBlank annotation will make sure that the request body must have a username.
	@NotBlank
	@JsonProperty("user_name")
	private String username;
	
	//This will make sure that we can only create or update password but can't fetch the password from DB for safety purposes.
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
