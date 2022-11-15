package com.mfpe.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mfpe.model.ProjectManagerDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

    private String SECRETKEY="${secretkey}";	//this is the secretkey value from the applications.properties file
	
	@Value("${tokenduration}")
	private int TOKENDURATION = 30;	//this is the expiration value for the token from the application.properties file

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);	//this method is used to extract the username from the token
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);	//this method is used to extract the expiration date from the token
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);	// this method is used to extract all the claims or subparts from the token
        return claimsResolver.apply(claims);
    }
    
    private Claims extractAllClaims(String token) {
    	String formated_token = token.trim().replaceAll("\0xfffd", "");
        return Jwts.parser().setSigningKey(SECRETKEY).parseClaimsJws(formated_token).getBody();	//this method is formatting all the claims that were exptracted
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());	//this method is verifying is the token is expired or not
    }

    public String generateToken(ProjectManagerDetails projectManagerDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, projectManagerDetails.getUsername());	//this method gives the generated/created token
        																	//it returns the createToken method
    }

    private String createToken(Map<String, Object> claims, String username) {

        return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKENDURATION*60*1000))
                .signWith(SignatureAlgorithm.HS256, SECRETKEY).compact();	//this method is called by generateToken method and it creates the token 
    }

    public Boolean validateToken(String token, ProjectManagerDetails projectManagerDetails) {
        final String username = extractUsername(token);
        return (username.equals(projectManagerDetails.getUsername()) && !isTokenExpired(token)); //this method gives a boolean value after validating the token
    }
}