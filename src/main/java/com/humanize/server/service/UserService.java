package com.humanize.server.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.dao.UserRepository;
import com.humanize.server.data.User;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public User login(User user) {
		User tempUser = repository.findByEmailId(user.getEmailId());

		if (user.getPassword().equals(tempUser.getPassword())) {
			return tempUser;
		}

		return null;
	}

	public User getUserdata(String emailId) {
		return repository.findByEmailId(emailId);
	}

	public User updateUser(User user) {
		return repository.save(user);
	}

	public User createUser(User user) {
		user.setUserId(UUID.randomUUID().toString());
		return repository.save(user);
	}

	public boolean likeContent(String userId, String contentId) {
		User user = repository.findByUserId(userId);
		if (user != null) {
			if (!user.getLikes().contains(contentId)) {
				user.getLikes().add(contentId);
				repository.save(user);
				return true;
			}
		}

		return false;
	}

	public boolean unlikeContent(String userId, String contentId) {
		User user = repository.findByUserId(userId);
		if (user != null) {
			if (user.getLikes().contains(contentId)) {
				user.getLikes().remove(contentId);
				repository.save(user);
				return true;
			}
		}

		return false;
	}

	public boolean bookmarkContent(String userId, String contentId) {
		User user = repository.findByUserId(userId);
		if (user != null) {
			if (!user.getBookmarks().contains(contentId)) {
				user.getBookmarks().add(contentId);
				repository.save(user);
				return true;
			}
		}

		return false;
	}

	public boolean unbookmarkContent(String userId, String contentId) {
		User user = repository.findByUserId(userId);
		if (user != null) {
			if (user.getBookmarks().contains(contentId)) {
				user.getBookmarks().remove(contentId);
				repository.save(user);
				return true;
			}
		}

		return false;
	}
}
