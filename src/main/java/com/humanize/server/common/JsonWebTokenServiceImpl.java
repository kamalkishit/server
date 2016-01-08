package com.humanize.server.common;

import java.security.Key;

import org.springframework.stereotype.Service;

import com.humanize.server.exception.TokenCreationException;
import com.humanize.server.exception.TokenValidationException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

@Service
public class JsonWebTokenServiceImpl implements TokenService {
	
	private static JsonWebTokenServiceImpl jsonWebToken = null;
	
	public static JsonWebTokenServiceImpl getInstance() {
		if (jsonWebToken == null) {
			jsonWebToken = new JsonWebTokenServiceImpl();
		}
		
		return jsonWebToken;
	}
	
	private Key key;
	
	public JsonWebTokenServiceImpl() {
		key = MacProvider.generateKey();
	}
	
	public String createToken(String emailId) throws TokenCreationException {
		try {
			String token = Jwts.builder().setSubject(emailId).signWith(SignatureAlgorithm.HS512, key).compact();
			String validate = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
			System.out.println(token);
			return token;
		} catch (Exception exception) {
			throw new TokenCreationException(0, null);
		}
	}
	
	public String validateToken(String token) throws TokenValidationException {
		try {
			System.out.println(token);
		    return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new TokenValidationException(0, null);
		}
	}
}
