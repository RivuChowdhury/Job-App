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
		String baseUsername=userDTO.getFullName().trim().toLowerCase().replace(" ",".");
		String actualUsername=baseUsername;

		int count=0;
		if(userRepo.existsByUsername(actualUsername)) {
			logger.warn("Username '{}' already exists",baseUsername);			
			while(userRepo.existsByUsername(actualUsername)) {
				count++;
				actualUsername=baseUsername+"."+count;
			}
			logger.info("Created '{}' for the new user",actualUsername);
		}
		
		User user=modelMapper.map(userDTO, User.class);
		user.setUsername(actualUsername);
		user.setPassword(encoder.encode(user.getPassword()));
		System.out.println(user.getPassword());
		userRepo.save(user);
		return "User saved successfully";
	}

}
