package com.humanize.server.common;

import com.humanize.server.exception.TokenCreationException;
import com.humanize.server.exception.TokenValidationException;

public interface TokenService {

	public String createToken(String emailId) throws TokenCreationException;
	public String validateToken(String token) throws TokenValidationException;
}
