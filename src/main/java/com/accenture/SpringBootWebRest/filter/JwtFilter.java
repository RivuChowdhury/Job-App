package com.accenture.SpringBootWebRest.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.accenture.SpringBootWebRest.service.CustomUserDetailsService;
import com.accenture.SpringBootWebRest.service.JwtService;

/*After generating the jwt token, now we need to validate that Jwt token sent by the user. In order to do that, we need to first remove the http basic 
authentication by deleting the line http.httpBasic(Customizer.withDefaults()); from the securityFilterChain method inside the Security.config file. Now, by 
default spring will provide UsernamePassword authentication filter(form login one) but we need to add a jwt filter before that filter so that after that 
jwt token is verified we can just create a Security context using principle and we can skip the UsernamePassword authentication as authentication is already 
present. Hence, we are implementing Jwt filter that will sit before the UsernamePassword filter and will verify our jwt token so that we don't have to 
authenticate again and again.*/
@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	CustomUserDetailsService userDetailsService;
	
	/*Here, we are overriding the method doFilterInternal of OncePerRequestFilter class. It takes 3 parameters which are HttpServletRequest request, 
	 HttpServletResponse response and FilterChain filterChain. Now, in order to verify our jwt token, we need to first get the jwt token. We can extract the 
	 jwt token from the request header sent by the user i.e. HttpServletRequest request parameter. */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		/*When we pass Jwt token for authentication, the jwt token gets added into the Header named "Authorization" in "Bearer " like format. 
		Hence, we are extracting the token from the Authorization header and storing it as a string. */
		String authHeader=request.getHeader("Authorization");
		String authToken=null;
		String username=null;
		//Checking if the token exists or not and removing the "Bearer " keyword from the token.
		if(authHeader!=null && authHeader.startsWith("Bearer ")) {
			//Removing the "Bearer " part from the authHeader string
			authToken=authHeader.substring(7);
			//Extracting the usermame from the token
			username=jwtService.extractUsername(authToken);
		}
		/*Here, we are checking if we are able to extract a username from the token or not and also checking whether the principle object inside the 
		Security Context is null or not. If it is not null then we will set the principle object of the SecurityContext so that the next security filter 
		could be skipped because the Security context o */
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails=userDetailsService.loadUserByUsername(username);
			/*Here, we are trying to validate the jwt token by extracting its username and comparing it with the DB using userDetailsService and also
			 * checking whether the jwt token is expired or not. If the token is authentication i.e. username of the token mathces with DB user and 
			 * the token has not expired then we can create an object of UserPrinciple of SecurityContext.*/
			if(jwtService.validateToken(username, userDetails,authToken)) {
				UsernamePasswordAuthenticationToken securityContextToken=new UsernamePasswordAuthenticationToken(userDetailsService,null,userDetails.getAuthorities());
				/*Here, we are adding more request related details to the securityContextToken as it has only the userDetailsService and its authorities.
				 NOTE: This line to not mandatory to add but it helps for logging purposes in the future. */
				securityContextToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				/*Here, we are adding the securityContextToken inside the SecurityContextHolder so that we can set the authentication for the jwt token.*/
				SecurityContextHolder.getContext().setAuthentication(securityContextToken);
			}
		}
		/*After verifying, authenticating the jwt token inside the jwt filter and creating an instance of UserPrinciple inside the SecurityContextHolder to hold the
		 set the authentication for the jwt token, we are calling the next filter in the fitlerChain. */
		filterChain.doFilter(request, response);
	}

}
