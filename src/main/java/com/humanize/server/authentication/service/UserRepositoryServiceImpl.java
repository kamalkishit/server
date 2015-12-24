package com.humanize.server.authentication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.authentication.exception.UserCreationFailedException;
import com.humanize.server.authentication.exception.UserDeletionFailedException;
import com.humanize.server.authentication.exception.UserNotFoundException;
import com.humanize.server.authentication.exception.UserUpdationFailedException;
import com.humanize.server.common.ExceptionConfig;
import com.humanize.server.dao.UserRepository;
import com.humanize.server.data.User;

@Service
public class UserRepositoryServiceImpl implements UserRepositoryService {

	@Autowired
	private UserRepository repository;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public User create(User user) throws UserCreationFailedException {
		user = repository.save(user);
		
		if (user != null) {
			return user;
		}
		
		throw new UserCreationFailedException(ExceptionConfig.USER_CREATION_ERROR_CODE, ExceptionConfig.USER_CREATION_EXCEPTION);
	}
	
	public User update(User user) throws UserUpdationFailedException {
		try {
			findByEmailId(user.getEmailId());
			
			user = repository.save(user);
			
			if (user != null) {
				return user;
			}
			
			throw new UserUpdationFailedException(ExceptionConfig.USER_UPDATION_ERROR_CODE, ExceptionConfig.USER_UPDATION_EXCEPTION);
		} catch (UserNotFoundException exception) {
			throw new UserUpdationFailedException(ExceptionConfig.USER_UPDATION_ERROR_CODE, ExceptionConfig.USER_UPDATION_EXCEPTION);
		}
	}
	
	public User findOne(String contentId) throws UserNotFoundException {
		try {
			return repository.findOne(contentId);
		} catch (Exception exception) {
			throw new UserNotFoundException(ExceptionConfig.USER_NOT_FOUND_ERROR_CODE, ExceptionConfig.USER_NOT_FOUND_EXCEPTION);
		}
	}
	
	public User findByEmailId(String emailId) throws UserNotFoundException {
		User user = repository.findByEmailId(emailId);
		
		if (user != null) {
			return user;
		}
		
		throw new UserNotFoundException(ExceptionConfig.USER_NOT_FOUND_ERROR_CODE, ExceptionConfig.USER_NOT_FOUND_EXCEPTION);
	}
	
	public void deleteByEmailId(String emailId) throws UserDeletionFailedException {
		try {
			User user = findByEmailId(emailId);
			repository.delete(user);
		} catch (Exception exception) {
			logger.error("", exception);
			throw new UserDeletionFailedException(ExceptionConfig.USER_DELETION_ERROR_CODE, ExceptionConfig.USER_DELETION_EXCEPTION);
		}
		
	}
	
	public void delete(User user) {
		repository.delete(user);
	}
}