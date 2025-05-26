package com.accenture.SpringBootWebRest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.accenture.SpringBootWebRest.model.User;
import com.accenture.SpringBootWebRest.repository.UserRepo;



@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;
	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12); //12 indicates the number of turns the password will be encrypted. 
	public User save(User user) {
		/*Here, we are encrypting the password given by the User .Hence, we are using getPassword to get the password and using
		 encoder to encode the password and set that encoded password as the new password in the database.*/
		user.setPassword(encoder.encode(user.getPassword()));
		System.out.println(user.getPassword());
		return userRepo.save(user);
	}

}
