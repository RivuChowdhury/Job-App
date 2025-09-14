package com.accenture.SpringBootWebRest.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain defalultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(customizer->customizer.disable());
		//http.authorizeHttpRequests(auth->auth.antMatchers("/jobPost/**", "/jobPosts", "/load", "/hello").permitAll().anyRequest().authenticated());
		//http.authorizeHttpRequests(auth->auth.anyRequest().authenticated());
		http.authorizeHttpRequests(auth->auth.antMatchers("/register").permitAll().anyRequest().authenticated());
		http.formLogin(Customizer.withDefaults());
		//http.oauth2Login(Customizer.withDefaults());
		
		return http.build();		
	}
 
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@SuppressWarnings("deprecation")
	@Bean
	public AuthenticationProvider authProvider() {
		
		DaoAuthenticationProvider provider =new DaoAuthenticationProvider();
		
		provider.setUserDetailsService(userDetailsService);
		
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		return provider;
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
