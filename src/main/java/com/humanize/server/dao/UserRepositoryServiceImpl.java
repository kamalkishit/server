package com.humanize.server.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.data.User;
import com.humanize.server.exception.ErrorCodes;
import com.humanize.server.exception.UserCreationException;
import com.humanize.server.exception.UserDeletionException;
import com.humanize.server.exception.UserNotFoundException;
import com.humanize.server.exception.UserUpdateException;

@Service
public class UserRepositoryServiceImpl implements UserRepositoryService {

	@Autowired
	private UserRepository repository;
	
	private static final Logger logger = LoggerFactory.getLogger(UserRepositoryServiceImpl.class);
	private static final String TAG = UserRepositoryServiceImpl.class.getSimpleName();
	
	public User create(User user) throws UserCreationException {
		user = repository.save(user);
		
		if (user != null) {
			return user;
		}
		
		throw new UserCreationException(ErrorCodes.USER_CREATION_ERROR);
	}
	
	public User update(User user) throws UserUpdateException {
		try {
			findByEmailId(user.getEmailId());
			
			user = repository.save(user);
			
			if (user != null) {
				return user;
			}
			
			throw new UserUpdateException(ErrorCodes.USER_UPDATE_ERROR);
		} catch (UserNotFoundException exception) {
			logger.error(TAG, exception);
			throw new UserUpdateException(ErrorCodes.USER_UPDATE_ERROR);
		}
	}
	
	public User findOne(String contentId) throws UserNotFoundException {
		try {
			return repository.findOne(contentId);
		} catch (Exception exception) {
			logger.error(TAG, exception);
			throw new UserNotFoundException(ErrorCodes.USER_NOT_FOUND_ERROR);
		}
	}
	
	public User findByEmailId(String emailId) throws UserNotFoundException {
		User user = repository.findByEmailId(emailId);
		
		if (user != null) {
			return user;
		}
		
		throw new UserNotFoundException(ErrorCodes.USER_NOT_FOUND_ERROR);
	}
	
	public void deleteByEmailId(String emailId) throws UserDeletionException {
		try {
			User user = findByEmailId(emailId);
			repository.delete(user);
		} catch (Exception exception) {
			logger.error(TAG, exception);
			throw new UserDeletionException(ErrorCodes.USER_DELETION_ERROR);
		}
		
	}
	
	public void delete(User user) throws UserDeletionException{
		repository.delete(user);
	}
}
