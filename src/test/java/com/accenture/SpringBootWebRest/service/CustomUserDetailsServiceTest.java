package com.accenture.SpringBootWebRest.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.accenture.SpringBootWebRest.repository.UserRepo;

public class CustomUserDetailsServiceTest {
	@Test
	public void firstTest() {
		System.out.println("Inside first test");
	}
	
	@Mock
	private UserRepo userRepo;
	
	@InjectMocks
	private CustomUserDetailsService customUserDetailsService;
	
	
	

}
