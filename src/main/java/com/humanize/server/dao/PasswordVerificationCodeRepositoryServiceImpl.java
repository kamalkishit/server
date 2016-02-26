package com.humanize.server.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.humanize.server.data.PasswordVerificationCode;
import com.humanize.server.exception.ErrorCodes;
import com.humanize.server.exception.PasswordVerificationCodeNotFoundException;

public class PasswordVerificationCodeRepositoryServiceImpl implements PasswordVerificationCodeRepositoryService {
	
	@Autowired
	PasswordVerificationCodeRepository repository;

	public PasswordVerificationCode findByEmailId(String emailId) throws PasswordVerificationCodeNotFoundException {
		PasswordVerificationCode passwordVerificationCode = repository.findByEmailId(emailId);
		
		if (passwordVerificationCode == null) {
			throw new PasswordVerificationCodeNotFoundException(ErrorCodes.CONTENT_CREATION_ERROR);
		}
		
		return passwordVerificationCode;
	}
}