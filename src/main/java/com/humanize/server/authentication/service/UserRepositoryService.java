package com.humanize.server.authentication.service;

import com.humanize.server.authentication.exception.UserCreationException;
import com.humanize.server.authentication.exception.UserDeletionException;
import com.humanize.server.authentication.exception.UserNotFoundException;
import com.humanize.server.authentication.exception.UserUpdationException;
import com.humanize.server.data.User;

public interface UserRepositoryService {
	
	public User create(User user) throws UserCreationException;
	public User update(User user) throws UserUpdationException;
	public User findOne(String contentId) throws UserNotFoundException;
	public User findByEmailId(String emailId) throws UserNotFoundException;
	public void deleteByEmailId(String emailId) throws UserDeletionException;
	public void delete(User user) throws UserDeletionException;
}
