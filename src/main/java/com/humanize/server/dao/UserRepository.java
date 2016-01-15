package com.humanize.server.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.humanize.server.data.User;

public interface UserRepository extends MongoRepository<User, String> {

	User findByEmailId(String emailId);
	User findByUserId(String userId);
}
