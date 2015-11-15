package com.humanize.server.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationManager {
	
	@Autowired
	JsonWebTokenService jsonWebTokenService;
	
	public void authenticate(String token) {
		if (token == null || token.length() == 0) {
			return;
		}
		
		String emailId = jsonWebTokenService.validateToken(token);
		
		if (emailId != null) {
			
		}
		
		/*List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();
		AUTHORITIES.add(new GrantedAuthorityImpl("ROLE_USER"));
		
		Authentication request = new UsernamePasswordAuthenticationToken("pandey.kishore@gmail.com", "pandey.kishore@gmail.com", AUTHORITIES);
        SecurityContextHolder.getContext().setAuthentication(request);*/
	}

}
