package com.mfpe.model;

import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AuthenticationRequest {
	
	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
	
}