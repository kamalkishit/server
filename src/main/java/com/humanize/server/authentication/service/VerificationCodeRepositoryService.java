package com.humanize.server.authentication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.authentication.dao.VerificationCodeRepository;
import com.humanize.server.authentication.data.TempPassword;
import com.humanize.server.authentication.data.VerificationCode;
import com.humanize.server.authentication.exception.TempPasswordCreationException;
import com.humanize.server.authentication.exception.TempPasswordNotFoundException;
import com.humanize.server.authentication.exception.TempPasswordUpdationException;
import com.humanize.server.authentication.exception.VerificationCodeCreationException;
import com.humanize.server.authentication.exception.VerificationCodeDeletionException;
import com.humanize.server.authentication.exception.VerificationCodeNotFoundException;
import com.humanize.server.authentication.exception.VerificationCodeUpdationException;
import com.humanize.server.common.ExceptionConfig;

@Service
public class VerificationCodeRepositoryService {
	
	@Autowired
	private VerificationCodeRepository repository;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public VerificationCode findByEmailId(String emailId) throws VerificationCodeNotFoundException {
		VerificationCode verificationCode = repository.findByEmailId(emailId);
		
		if (verificationCode != null) {
			return verificationCode;
		}
		
		throw new VerificationCodeNotFoundException(ExceptionConfig.VERIFICATION_CODE_NOT_FOUND_ERROR_CODE, ExceptionConfig.VERIFICATION_CODE_NOT_FOUND_EXCEPTION);
	}
	
	public VerificationCode create(VerificationCode verificationCode) throws VerificationCodeCreationException {
		verificationCode = repository.save(verificationCode);
		
		if (verificationCode != null) {
			return verificationCode;
		}
		
		throw new VerificationCodeCreationException(ExceptionConfig.VERIFICATION_CODE_CREATION_ERROR_CODE, ExceptionConfig.VERIFICATION_CODE_CREATION_EXCEPTION);
	}
	
	public VerificationCode createOrUpdate(VerificationCode verificationCode) throws VerificationCodeCreationException, VerificationCodeUpdationException {
		try {
			VerificationCode tempVerificationCode = findByEmailId(verificationCode.getEmailId());
			
			tempVerificationCode.setVerificationCode(verificationCode.getVerificationCode());
			
			tempVerificationCode = repository.save(tempVerificationCode);
			
			if (tempVerificationCode == null) {
				throw new VerificationCodeUpdationException(ExceptionConfig.VERIFICATION_CODE_UPDATION_ERROR_CODE, ExceptionConfig.VERIFICATION_CODE_UPDATION_EXCEPTION);
				
			}
			
			return tempVerificationCode;
		} catch (VerificationCodeNotFoundException exception) {
			verificationCode = repository.save(verificationCode);
			
			if (verificationCode == null) {
				throw new VerificationCodeCreationException(ExceptionConfig.VERIFICATION_CODE_CREATION_ERROR_CODE, ExceptionConfig.VERIFICATION_CODE_CREATION_EXCEPTION);
			}
			
			return verificationCode;
		} 
	}
	
	public VerificationCode update(VerificationCode verificationCode) throws VerificationCodeUpdationException {
		verificationCode = repository.save(verificationCode);
		
		if (verificationCode != null) {
			return verificationCode;
		}
		
		throw new VerificationCodeUpdationException(ExceptionConfig.VERIFICATION_CODE_UPDATION_ERROR_CODE, ExceptionConfig.VERIFICATION_CODE_UPDATION_EXCEPTION);
	}
	
	public void deleteByEmailId(String emailId) throws VerificationCodeDeletionException {
		try {
			VerificationCode verificationCode = findByEmailId(emailId);
			repository.delete(verificationCode);
		} catch (Exception exception) {
			logger.error("", exception);
			throw new VerificationCodeDeletionException(ExceptionConfig.VERIFICATION_CODE_DELETION_ERROR_CODE, ExceptionConfig.VERIFICATION_CODE_DELETION_EXCEPTION);
		}
		
	}
	
	public void delete(VerificationCode verificationCode) {
		repository.delete(verificationCode);
	}
}
