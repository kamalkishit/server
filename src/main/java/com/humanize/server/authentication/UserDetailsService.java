package com.humanize.server.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.humanize.server.data.User;
import com.humanize.server.service.UserService;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private UserService userService;

	private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

	@Override
	public final User loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			final User user = userService.findByEmailId(username);
			if (user == null) {
				throw new UsernameNotFoundException("user not found");
			}
			detailsChecker.check(user);
			return user;
		} catch (Exception exception) {
			throw new UsernameNotFoundException("user not found");
		}
	}
}
