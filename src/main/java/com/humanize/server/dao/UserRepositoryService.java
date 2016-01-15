package com.humanize.server.dao;

import com.humanize.server.data.User;
import com.humanize.server.exception.UserCreationException;
import com.humanize.server.exception.UserDeletionException;
import com.humanize.server.exception.UserNotFoundException;
import com.humanize.server.exception.UserUpdateException;

public interface UserRepositoryService {
	
	User create(User user) throws UserCreationException;
	User update(User user) throws UserUpdateException;
	User findOne(String contentId) throws UserNotFoundException;
	User findByEmailId(String emailId) throws UserNotFoundException;
	void deleteByEmailId(String emailId) throws UserDeletionException;
	void delete(User user) throws UserDeletionException;
}
