package com.accenture.SpringBootWebRest.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.accenture.SpringBootWebRest.dto.UserDTO;
import com.accenture.SpringBootWebRest.entity.User;
import com.accenture.SpringBootWebRest.repository.UserRepo;



@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	private static final Logger logger=LoggerFactory.getLogger(UserService.class);
	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12); //12 indicates the number of turns the password will be encrypted. 
	public String save(UserDTO userDTO) {
		User user=modelMapper.map(userDTO, User.class);
		/*Here, we are encrypting the password given by the User .Hence, we are using getPassword to get the password and using
		 encoder to encode the password and set that encoded password as the new password in the database.*/
		if(userRepo.existsByUsername(userDTO.getUsername())) {
			/*By default SpringBoot has info,warn and error as the logging levels.To user debug and trace we need to configure
			 them in the logback.xml file.*/
			logger.info("Infoooooooooooooooo");
			logger.warn("Warnnnnnnnnnnnnnnnn");
			logger.error("Error occured for {}",userDTO.getUsername());//Unlike System.out.println(),in Logger we are using ,(comma) instead of +(concatenation).
			logger.debug("Debugggggggggggggg");
			logger.trace("Traceeeeeeeeeeeeee");
			throw new RuntimeException("User with username "+ userDTO.getUsername()+" already exists");
		}
		user.setPassword(encoder.encode(user.getPassword()));
		System.out.println(user.getPassword());
		user.setRole("ROLE_USER");
		userRepo.save(user);
		return "User saved successfully";
		/*try {
			return userRepo.save(user);
		}
		catch(Exception e) {
			System.out.println(e);
			throw new RuntimeException("User with ID " + user.getId() + " already exists.");
		}*/
	}

}
