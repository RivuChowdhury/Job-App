package com.accenture.SpringBootWebRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.SpringBootWebRest.model.User;
import com.accenture.SpringBootWebRest.service.UserService;



@RestController
public class UserController {
	@Autowired
	UserService userService;
	@PostMapping("register")
	public User register(@RequestBody User user) {
		return userService.save(user);		
	}

}
