package com.humanize.server.common;

import java.security.Key;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.MacProvider;

@Service
public class JsonWebTokenService {
	
	private Key key;
	
	public JsonWebTokenService() {
		key = MacProvider.generateKey();
	}
	
	public String createToken(String emailId) {
		return Jwts.builder().setSubject(emailId).signWith(SignatureAlgorithm.HS512, key).compact();
	}
	
	public String validateToken(String token) {
		try {
		    Jwts.parser().setSigningKey(key).parseClaimsJws(token).toString();
		} catch (SignatureException e) {
			
		}
		
		return null;
	}
}
