package com.accenture.SpringBootWebRest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.accenture.SpringBootWebRest.model.User;
import com.accenture.SpringBootWebRest.repository.UserRepo;



@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;
	
	private static final Logger logger=LoggerFactory.getLogger(UserService.class);
	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12); //12 indicates the number of turns the password will be encrypted. 
	public User save(User user) {
		/*Here, we are encrypting the password given by the User .Hence, we are using getPassword to get the password and using
		 encoder to encode the password and set that encoded password as the new password in the database.*/
		if(userRepo.existsById(user.getId())) {
			/*By default SpringBoot has info,warn and error as the logging levels.To user debug and trace we need to configure
			 them in the logback.xml file.*/
			logger.info("Infoooooooooooooooo");
			logger.warn("Warnnnnnnnnnnnnnnnn");
			logger.error("Error occured for {}",user.getUsername());//Unlike System.out.println(),in Logger we are using ,(comma) instead of +(concatenation).
			logger.debug("Debugggggggggggggg");
			logger.trace("Traceeeeeeeeeeeeee");
			throw new RuntimeException("User with ID "+ user.getId()+" already exists");
		}
		user.setPassword(encoder.encode(user.getPassword()));
		System.out.println(user.getPassword());
		return userRepo.save(user);
		/*try {
			return userRepo.save(user);
		}
		catch(Exception e) {
			System.out.println(e);
			throw new RuntimeException("User with ID " + user.getId() + " already exists.");
		}*/
	}

}
