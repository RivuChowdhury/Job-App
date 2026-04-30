package com.accenture.SpringBootWebRest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.SpringBootWebRest.dto.AuthRequestDTO;
import com.accenture.SpringBootWebRest.service.JwtService;

@RestController
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtService jwtService;
	
	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequestDTO authRequestDTO) {
		try {
		 authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(),authRequestDTO.getPassword()));
		  return jwtService.generateToken(authRequestDTO.getUsername());
		}
		catch(Exception e) {
			return "Authentication failed";
		}
		//return "JWT token";
	}
	

}
