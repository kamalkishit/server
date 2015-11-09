package com.humanize.server.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.authentication.data.InvitationCode;
import com.humanize.server.authentication.exception.UserCreationException;
import com.humanize.server.authentication.exception.UserNotFoundException;
import com.humanize.server.authentication.exception.UserUpdationException;
import com.humanize.server.common.ExceptionConfig;
import com.humanize.server.dao.UserRepository;
import com.humanize.server.data.User;

@Service
public class UserRepositoryService {
	
	@Autowired
	private UserRepository repository;
	
	public User create(User user) {
		user = repository.save(user);
		
		if (user != null) {
			return user;
		}
		
		throw new UserCreationException(ExceptionConfig.USER_CREATION_ERROR_CODE, ExceptionConfig.USER_CREATION_EXCEPTION);
	}
	
	public User update(User user) {
		user = repository.save(user);
		
		if (user != null) {
			return user;
		}
		
		throw new UserUpdationException(ExceptionConfig.USER_UPDATION_ERROR_CODE, ExceptionConfig.USER_UPDATION_EXCEPTION);
	}
	
	public User findByEmailId(String emailId) {
		User user = repository.findByEmailId(emailId);
		
		if (user != null) {
			return user;
		}
		
		throw new UserNotFoundException(ExceptionConfig.USER_NOT_FOUND_ERROR_CODE, ExceptionConfig.USER_NOT_FOUND_EXCEPTION);
	}
	
	public void deleteByEmailId(String emailId) {
		User user = findByEmailId(emailId);
		repository.delete(user);
	}
	
	public void delete(User user) {
		repository.delete(user);
	}
	


}
