package com.humanize.server.service;

import com.humanize.server.exception.TokenCreationException;
import com.humanize.server.exception.TokenValidationException;

public interface TokenService {

	String createToken(String emailId) throws TokenCreationException;	
	String validateToken(String token) throws TokenValidationException;
}
