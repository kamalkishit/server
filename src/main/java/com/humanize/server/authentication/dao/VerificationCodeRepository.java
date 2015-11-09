package com.humanize.server.authentication.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.humanize.server.authentication.data.VerificationCode;


public interface VerificationCodeRepository extends MongoRepository<VerificationCode, String>{
	
	public VerificationCode findByEmailId(String emailId);
}
