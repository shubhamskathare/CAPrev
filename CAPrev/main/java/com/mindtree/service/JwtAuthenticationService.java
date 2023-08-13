package com.mindtree.service;


import com.mindtree.dto.UserDto;
import com.mindtree.exception.UnauthorizedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtAuthenticationService {
	
	@Value("${user}")
	String username;
	
	@Value("${password}")
	String password;
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationService.class);

	
	private static final long EXPIRATION_TIME = 900000;
	
	
	public String authenticateUser(UserDto user) throws UnauthorizedException {

		if(!(user.getUsername().equals(username))) {
			logger.debug("Username is invalid");
			throw new UnauthorizedException("Invalid Credentials");
		}
		if(!(user.getPassword().equals(password))){
			logger.debug("Password is invalid");
			throw new UnauthorizedException("Invalid Credentials");
			
		}
		return Jwts.builder().setSubject(username).claim("roles", "user").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey").setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).compact();
		
		
	}

}
