package com.humanize.server.service;

import java.security.Key;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.humanize.server.exception.ErrorCodes;
import com.humanize.server.exception.TokenCreationException;
import com.humanize.server.exception.TokenValidationException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

@Service
public class JsonWebTokenServiceImpl implements TokenService {
	
	private Key key;
	private static JsonWebTokenServiceImpl jsonWebToken = null;
	
	private static final Logger logger = LoggerFactory.getLogger(JsonWebTokenServiceImpl.class);
	private static final String TAG = JsonWebTokenServiceImpl.class.getSimpleName();
	
	public static JsonWebTokenServiceImpl getInstance() {
		if (jsonWebToken == null) {
			jsonWebToken = new JsonWebTokenServiceImpl();
		}
		
		return jsonWebToken;
	}
	
	public JsonWebTokenServiceImpl() {
		key = MacProvider.generateKey();
	}
	
	public String createToken(String emailId) throws TokenCreationException {
		try {
			String token = Jwts.builder().setSubject(emailId).signWith(SignatureAlgorithm.HS512, key).compact();
			Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
			return token;
		} catch (Exception exception) {
			logger.error(TAG, exception);
			throw new TokenCreationException(ErrorCodes.TOKEN_CREATION_ERROR);
		}
	}
	
	public String validateToken(String token) throws TokenValidationException {
		try {
		    return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
		} catch (Exception exception) {
			logger.error(TAG, exception);
			throw new TokenValidationException(ErrorCodes.TOKEN_VALIDATION_ERROR);
		}
	}
}
