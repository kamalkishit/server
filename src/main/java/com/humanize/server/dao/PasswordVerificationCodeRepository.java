package com.humanize.server.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.humanize.server.data.PasswordVerificationCode;
import com.humanize.server.data.VerificationCode;

public interface PasswordVerificationCodeRepository extends MongoRepository<VerificationCode, String> {

	PasswordVerificationCode findByEmailId(String emailId);
}
