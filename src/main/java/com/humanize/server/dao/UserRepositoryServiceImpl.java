package com.humanize.server.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.data.User;
import com.humanize.server.exception.UserCreationException;
import com.humanize.server.exception.UserDeletionException;
import com.humanize.server.exception.UserNotFoundException;
import com.humanize.server.exception.UserUpdateException;

@Service
public class UserRepositoryServiceImpl implements UserRepositoryService {

	@Autowired
	private UserRepository repository;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public User create(User user) throws UserCreationException {
		user = repository.save(user);
		
		if (user != null) {
			return user;
		}
		
		throw new UserCreationException(0, null);
	}
	
	public User update(User user) throws UserUpdateException {
		try {
			findByEmailId(user.getEmailId());
			
			user = repository.save(user);
			
			if (user != null) {
				return user;
			}
			
			throw new UserUpdateException(0, null);
		} catch (UserNotFoundException exception) {
			throw new UserUpdateException(0, null);
		}
	}
	
	public User findOne(String contentId) throws UserNotFoundException {
		try {
			return repository.findOne(contentId);
		} catch (Exception exception) {
			throw new UserNotFoundException(0, null);
		}
	}
	
	public User findByEmailId(String emailId) throws UserNotFoundException {
		User user = repository.findByEmailId(emailId);
		
		if (user != null) {
			return user;
		}
		
		throw new UserNotFoundException(0, null);
	}
	
	public void deleteByEmailId(String emailId) throws UserDeletionException {
		try {
			User user = findByEmailId(emailId);
			repository.delete(user);
		} catch (Exception exception) {
			logger.error("", exception);
			throw new UserDeletionException(0, null);
		}
		
	}
	
	public void delete(User user) throws UserDeletionException{
		repository.delete(user);
	}
}
