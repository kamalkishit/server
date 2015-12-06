package com.humanize.server.authentication.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.humanize.server.authentication.data.TempPassword;

public interface TempPasswordRepository extends MongoRepository<TempPassword, String>{
	
	public TempPassword findByEmailId(String emailId);
}
 