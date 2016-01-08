package com.humanize.server.authentication.service;

import com.humanize.server.authentication.exception.UserCreationException;
import com.humanize.server.authentication.exception.UserDeletionException;
import com.humanize.server.authentication.exception.UserNotFoundException;
import com.humanize.server.authentication.exception.UserUpdateException;
import com.humanize.server.data.User;

public interface UserRepositoryService {
	
	User create(User user) throws UserCreationException;
	User update(User user) throws UserUpdateException;
	User findOne(String contentId) throws UserNotFoundException;
	User findByEmailId(String emailId) throws UserNotFoundException;
	void deleteByEmailId(String emailId) throws UserDeletionException;
	void delete(User user) throws UserDeletionException;
}
