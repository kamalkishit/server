package com.humanize.server.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.humanize.server.data.User;

public interface UserRepository extends MongoRepository<User, String> {

	public User findByEmailId(String emailId);

	public User findByUserId(String userId);
}
