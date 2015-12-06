package com.humanize.server.authentication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.authentication.exception.UserCreationException;
import com.humanize.server.authentication.exception.UserDeletionException;
import com.humanize.server.authentication.exception.UserNotFoundException;
import com.humanize.server.authentication.exception.UserUpdationException;
import com.humanize.server.common.ExceptionConfig;
import com.humanize.server.dao.UserRepository;
import com.humanize.server.data.User;

@Service
public class UserRepositoryService {
	
	@Autowired
	private UserRepository repository;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public User create(User user) throws UserCreationException {
		user = repository.save(user);
		
		if (user != null) {
			return user;
		}
		
		throw new UserCreationException(ExceptionConfig.USER_CREATION_ERROR_CODE, ExceptionConfig.USER_CREATION_EXCEPTION);
	}
	
	public User update(User user) throws UserUpdationException {
		try {
			findByEmailId(user.getEmailId());
			
			user = repository.save(user);
			
			if (user != null) {
				return user;
			}
			
			throw new UserUpdationException(ExceptionConfig.USER_UPDATION_ERROR_CODE, ExceptionConfig.USER_UPDATION_EXCEPTION);
		} catch (UserNotFoundException exception) {
			throw new UserUpdationException(ExceptionConfig.USER_UPDATION_ERROR_CODE, ExceptionConfig.USER_UPDATION_EXCEPTION);
		}
	}
	
	public User findByEmailId(String emailId) throws UserNotFoundException {
		User user = repository.findByEmailId(emailId);
		
		if (user != null) {
			return user;
		}
		
		throw new UserNotFoundException(ExceptionConfig.USER_NOT_FOUND_ERROR_CODE, ExceptionConfig.USER_NOT_FOUND_EXCEPTION);
	}
	
	public void deleteByEmailId(String emailId) throws UserDeletionException {
		try {
			User user = findByEmailId(emailId);
			repository.delete(user);
		} catch (Exception exception) {
			logger.error("", exception);
			throw new UserDeletionException(ExceptionConfig.USER_DELETION_ERROR_CODE, ExceptionConfig.USER_DELETION_EXCEPTION);
		}
		
	}
	
	public void delete(User user) {
		repository.delete(user);
	}
	


}
