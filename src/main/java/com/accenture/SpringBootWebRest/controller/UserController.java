package com.accenture.SpringBootWebRest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.SpringBootWebRest.dto.UserDTO;
import com.accenture.SpringBootWebRest.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@Tag(name="User APIs", description="Register users using Spring Security")
public class UserController {
	@Autowired
	UserService userService;
	@PostMapping("register")
	@Operation(summary="Login users into the Job App")
	public ResponseEntity<String> register(@Valid @RequestBody UserDTO user) {
		String response= userService.save(user);	
		return ResponseEntity.ok(response);
	}

}
