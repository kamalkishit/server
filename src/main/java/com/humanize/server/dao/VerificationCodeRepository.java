package com.humanize.server.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.humanize.server.data.VerificationCode;

public interface VerificationCodeRepository  extends MongoRepository<VerificationCode, String> {

	VerificationCode findByEmailId(String emailId);
}
