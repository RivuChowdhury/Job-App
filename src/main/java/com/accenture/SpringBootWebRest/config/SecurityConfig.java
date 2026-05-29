package com.accenture.SpringBootWebRest.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.accenture.SpringBootWebRest.filter.JwtFilter;
import com.accenture.SpringBootWebRest.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	JwtFilter jwtFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	    http.csrf(csrf -> csrf.disable());

	    http.authorizeHttpRequests(auth -> auth
	        .requestMatchers(
	            "/register","/authenticate",
	            "/swagger-ui/**",
	            "/swagger-ui.html",
	            "/v2/api-docs",
	            "/swagger-resources/**",
	            "/webjars/**"
	        ).permitAll()
	        .anyRequest().authenticated()
	    );

	    // Use form login for user-based app
	    //http.formLogin(Customizer.withDefaults());
	    
	    //Used for postman based "Basic Auth" api testing with username and password
	    //http.httpBasic(Customizer.withDefaults());
	    
	    /*Here,we are adding the Jwt filter before the UsernamePasswordAuthentication so that we can make our project stateless so that username and 
	    password is not needed every time the user logs into their account.*/
	    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


	    return http.build();
	}

	/*@Bean
	public SecurityFilterChain defalultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(customizer->customizer.disable());
		//http.authorizeHttpRequests(auth->auth.antMatchers("/jobPost/**", "/jobPosts", "/load", "/hello").permitAll().anyRequest().authenticated());
		//http.authorizeHttpRequests(auth->auth.anyRequest().authenticated());
		http.authorizeHttpRequests(auth->auth.antMatchers("/register").permitAll().anyRequest().authenticated());
		http.formLogin(Customizer.withDefaults());
		//http.oauth2Login(Customizer.withDefaults());
		
		return http.build();		
	}*/
 
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}
	
	@SuppressWarnings("deprecation")
	@Bean
	public AuthenticationManager authManager(CustomUserDetailsService userDetailsService,PasswordEncoder passwordEncoder) {
		
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		
		provider.setUserDetailsService(userDetailsService);
		
		provider.setPasswordEncoder(passwordEncoder);
		
		return new ProviderManager(provider);
	}
	/*@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(customizer->customizer.disable());
		
		
		http.authorizeHttpRequests(request->request.antMatchers("/swagger-ui/**",
				 "/swagger-ui.html",
				 "/v2/api-docs",
				 "/swagger-resources/**",
				 "/webjars/**").permitAll().anyRequest().authenticated());
		
		http.httpBasic(Customizer.withDefaults());
		
		
		http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return http.build();
	}*/
	
	/*By default,Spring Security uses UserDetailsService class in the background to check for username and password in 
	 application.properties file.But in that case,we can have only 1 user. In order to have multiple users for our project,
	 we need to create our own userDetailsService class and add our users.*/
	/*@Bean
	public UserDetailsService userDetailsService() {
		@SuppressWarnings("deprecation")
		UserDetails user=User.withDefaultPasswordEncoder() //This method is used to tell that as of now we are not using any encoder
		                     .username("Rivu")
		                     .password("Rivu@12345")
		                     .roles("USER")
		                     .build();
		UserDetails admin=User.withDefaultPasswordEncoder() 
                .username("admin")
                .password("adim@1111")
                .roles("ADMIN","USER")
                .build();
		return new InMemoryUserDetailsManager(user,admin);
	}*/

}
