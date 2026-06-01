package com.accenture.SpringBootWebRest.dto;



import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public class UserDTO {	

	//This @NotBlank annotation will make sure that the request body must have a username.
	@NotBlank
	@JsonProperty("email")
	private String email;
	
	//This will make sure that we can only create or update password but can't fetch the password from DB for safety purposes.
	@NotBlank
	@JsonProperty(value="password",access=JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	@NotBlank
	@JsonProperty(value="fullname")
    private String fullName;
	
	@JsonProperty(value="DOB")
	private LocalDate dateOfBirth;
	
	@JsonProperty(value="created_at")
	private LocalDateTime createdAt;
	
	@JsonProperty(value="last_login_at")
	private LocalDateTime lastLoginAt;
	
	@JsonProperty(value="role")
	private String role;
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getLastLoginAt() {
		return lastLoginAt;
	}

	public void setLastLoginAt(LocalDateTime lastLoginAt) {
		this.lastLoginAt = lastLoginAt;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	

}
