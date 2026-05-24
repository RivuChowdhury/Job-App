package com.accenture.SpringBootWebRest.service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
	
    private String secretKey;
    private final long EXPIRATION_TIME=1000*60*5;
	
	public JwtService(){
        secretKey = generateSecretKey();
    }

    public String generateSecretKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey secretKey = keyGen.generateKey();
            System.out.println("Secret Key : " + secretKey.toString());
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating secret key", e);
        }
    }
    
    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
	
	public String generateToken(String username) {
		
		return Jwts.builder()
				   .setSubject(username)
				   .setIssuedAt(new Date(System.currentTimeMillis()))
				   .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
		           .signWith(getKey(),SignatureAlgorithm.HS256)
		           .compact();		           
		           
	}
	
	/*This method will be used to extract out the jwt token details.*/
	public Claims extractClaims(String authToken) {
		return Jwts.parserBuilder()
		        .setSigningKey(getKey())
		        .build()
		        .parseClaimsJws(authToken)
		        .getBody();
	}
	
	/*THis method will be used to get the username from the Jwt token. Here, we will call the extractClaims method that will extract out the details from
	 * the jwt token and we will store those claims inside the variable body and then we fill get the username from the body using getSubject() method 
	 * because we have initially passed username using setSubject() method.*/
	public String extractUsername(String authToken) {
		/*Here, we are calling */
		Claims body=extractClaims(authToken);

		/*As, while generating the token we have passed username as our subject using setSubject(username) method hence here we are getting the username
		 from the subject of the body using getSubject() method.*/		
		return body.getSubject();
	}
	
	/*This method will be used to validate the jwt token using 3 parameters namely i) username we get after extracting our jwt token
	ii)userDetailsService object so that we can fetch the actual user details from the DB and verify with the extracted username of
	the jwt token iii)The jwt token itself so that we can check if the jwt token is expired or not.  */
	public boolean validateToken(String username,UserDetails userDetails,String authToken) {
		/*Checking if the username is same as username in DB through userDetails method loadByUsername(username) and whether the jwt token is
		 expired or not  */
		return username.equals(userDetails.getUsername()) && !isTokenExpired(authToken);
			
		
		
		
	}
	
	//Checking if the token is expired or not.
	public boolean isTokenExpired(String authToken) {
		/*Here, we are calling the extractClaims method and passing the jwt token and this extractClaims method will provide the jwt token details which
		and we will use the getExpiration method to get the expiration time of the jwt token and .before(new Date()) which will verify with the 
		current time that whether the jwt token has expired or not.*/
		return extractClaims(authToken).getExpiration().before(new Date());
	}
	
	

}
