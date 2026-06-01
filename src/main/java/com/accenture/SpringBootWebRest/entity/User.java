package com.accenture.SpringBootWebRest.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="users")
public class User implements UserDetails{
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID userId;
	
	@Column(nullable=false,unique=true)
	private String email;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false)
	private String fullName;
	
	private LocalDate dateOfBirth;
	
	@Column(nullable=false)
	private LocalDateTime createdAt;
	
	private LocalDateTime lastLoginAt;
	
	@Column(nullable=false)
	private String role;
	
	@Column(nullable=false,unique=true)
	private String username;	
	
	@Column(nullable=false,unique=true)
	private UUID profileId;
		
	
	public User() {
		this.profileId=UUID.randomUUID();
	}
	
	public UUID getProfileId() {
		return profileId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username=username;
	}
	public UUID getUserId() {
		return userId;
	}
	public void setUserId(UUID userId) {
		this.userId = userId;
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
	
	/* We are calculating the age at the runtime to make sure that the age remained correct value every time the user logs in from Current Date to DOB.
	  If we create a separate age column in the DB, there is a chance that column will contain stale value as age gets updated every year and not a 
	  permanent value */
	@Transient
	public int getAge() {
		if(dateOfBirth==null) {
			return 0;
		}
		return Period.between(dateOfBirth, LocalDate.now()).getYears();
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
		this.role ="ROLE_"+role;
	}
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

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Arrays.asList(new SimpleGrantedAuthority(role));
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
