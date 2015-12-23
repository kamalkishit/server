package com.humanize.server.authentication.service;

import com.humanize.server.authentication.exception.UserCreationFailedException;
import com.humanize.server.authentication.exception.UserDeletionFailedException;
import com.humanize.server.authentication.exception.UserNotFoundException;
import com.humanize.server.authentication.exception.UserUpdationFailedException;
import com.humanize.server.data.User;

public interface UserRepositoryService {
	
	public User create(User user) throws UserCreationFailedException;
	public User update(User user) throws UserUpdationFailedException;
	public User findOne(String contentId) throws UserNotFoundException;
	public User findByEmailId(String emailId) throws UserNotFoundException;
	public void deleteByEmailId(String emailId) throws UserDeletionFailedException;
	public void delete(User user);
}
