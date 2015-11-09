package com.humanize.server.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.authentication.dao.VerificationCodeRepository;
import com.humanize.server.authentication.data.VerificationCode;
import com.humanize.server.authentication.exception.VerificationCodeCreationException;
import com.humanize.server.authentication.exception.VerificationCodeNotFoundException;
import com.humanize.server.authentication.exception.VerificationCodeUpdationException;
import com.humanize.server.common.ExceptionConfig;

@Service
public class VerificationCodeRepositoryService {
	
	@Autowired
	private VerificationCodeRepository repository;
	
	public VerificationCode findByEmailId(String emailId) {
		VerificationCode verificationCode = repository.findByEmailId(emailId);
		
		if (verificationCode != null) {
			return verificationCode;
		}
		
		throw new VerificationCodeNotFoundException(ExceptionConfig.VERIFICATION_CODE_NOT_FOUND_ERROR_CODE, ExceptionConfig.VERIFICATION_CODE_NOT_FOUND_EXCEPTION);
	}
	
	public VerificationCode create(VerificationCode verificationCode) {
		verificationCode = repository.save(verificationCode);
		
		if (verificationCode != null) {
			return verificationCode;
		}
		
		throw new VerificationCodeCreationException(ExceptionConfig.VERIFICATION_CODE_CREATION_ERROR_CODE, ExceptionConfig.VERIFICATION_CODE_CREATION_EXCEPTION);
	}
	
	public VerificationCode update(VerificationCode verificationCode) {
		verificationCode = repository.save(verificationCode);
		
		if (verificationCode != null) {
			return verificationCode;
		}
		
		throw new VerificationCodeUpdationException(ExceptionConfig.VERIFICATION_CODE_UPDATION_ERROR_CODE, ExceptionConfig.VERIFICATION_CODE_UPDATION_EXCEPTION);
	}
	
	public void deleteByEmailId(String emailId) {
		VerificationCode verificationCode = findByEmailId(emailId);
		repository.delete(verificationCode);
	}
	
	public void delete(VerificationCode verificationCode) {
		repository.delete(verificationCode);
	}
}
