package com.accenture.SpringBootWebRest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.accenture.SpringBootWebRest.repository.UserRepo;


@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/* As, User.java class already implements the UserDetails interface hence we can directly do upcasting by casting a subclass object of User type in this
		 case to a superclass reference of UserDetails interface.*/
		UserDetails user= userRepo.findByUsername(username);
		if(user==null) {
			System.out.println("User doesn't exist");
			throw new UsernameNotFoundException("User not found");
		} 
		return user;
	}

}
