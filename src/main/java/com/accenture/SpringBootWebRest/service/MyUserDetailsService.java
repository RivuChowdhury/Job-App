package com.accenture.SpringBootWebRest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.accenture.SpringBootWebRest.model.User;
import com.accenture.SpringBootWebRest.model.UserPrinciple;
import com.accenture.SpringBootWebRest.repository.UserRepo;


@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user= userRepo.findByUsername(username);
		if(user==null) {
			System.out.println("User doesn't exist");
			throw new UsernameNotFoundException("User not found");
		}
		return new UserPrinciple(user);
	}

}
