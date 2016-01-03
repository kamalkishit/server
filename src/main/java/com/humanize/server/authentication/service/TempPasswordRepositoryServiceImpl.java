package com.humanize.server.authentication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.authentication.dao.TempPasswordRepository;
import com.humanize.server.authentication.data.TempPassword;
import com.humanize.server.authentication.exception.TempPasswordCreationException;
import com.humanize.server.authentication.exception.TempPasswordDeletionException;
import com.humanize.server.authentication.exception.TempPasswordNotFoundException;
import com.humanize.server.authentication.exception.TempPasswordUpdationException;
import com.humanize.server.common.ExceptionConfig;

@Service
public class TempPasswordRepositoryServiceImpl implements TempPasswordRepositoryService {

	@Autowired
	private TempPasswordRepository repository;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public TempPassword create(TempPassword tempPassword) throws TempPasswordCreationException {
		tempPassword = repository.save(tempPassword);
		
		if (tempPassword != null) {
			return tempPassword;
		}
		
		throw new TempPasswordCreationException(ExceptionConfig.TEMP_PASSWORD_CREATION_ERROR_CODE, ExceptionConfig.TEMP_PASSWORD_CREATION_EXCEPTION);
	}
	
	public TempPassword createOrUpdate(TempPassword tempPassword) throws TempPasswordCreationException, TempPasswordUpdationException {
		try {
			TempPassword tempTempPassword = findByEmailId(tempPassword.getEmailId());
			
			tempTempPassword.setTempPassword(tempPassword.getTempPassword());
			
			tempTempPassword = repository.save(tempTempPassword);
			
			if (tempTempPassword == null) {
				throw new TempPasswordUpdationException(ExceptionConfig.TEMP_PASSWORD_UPDATION_ERROR_CODE, ExceptionConfig.TEMP_PASSWORD_UPDATION_EXCEPTION);
				
			}
			
			return tempTempPassword;
		} catch (TempPasswordNotFoundException exception) {
			tempPassword = repository.save(tempPassword);
			
			if (tempPassword == null) {
				throw new TempPasswordCreationException(ExceptionConfig.TEMP_PASSWORD_CREATION_ERROR_CODE, ExceptionConfig.TEMP_PASSWORD_CREATION_EXCEPTION);
			}
			
			return tempPassword;
		} 
	}
	
	public TempPassword update(TempPassword tempPassword) throws TempPasswordUpdationException {
		try {
			findByEmailId(tempPassword.getEmailId());
			
			tempPassword = repository.save(tempPassword);
			
			if (tempPassword != null) {
				return tempPassword;
			}
			
			throw new TempPasswordUpdationException(ExceptionConfig.TEMP_PASSWORD_UPDATION_ERROR_CODE, ExceptionConfig.TEMP_PASSWORD_UPDATION_EXCEPTION);
		} catch (Exception exception) {
			throw new TempPasswordUpdationException(ExceptionConfig.TEMP_PASSWORD_UPDATION_ERROR_CODE, ExceptionConfig.TEMP_PASSWORD_UPDATION_EXCEPTION);
		}
	}
	
	public TempPassword findByEmailId(String emailId) throws TempPasswordNotFoundException {
		TempPassword tempPassword = repository.findByEmailId(emailId);
		
		if (tempPassword != null) {
			return tempPassword;
		}
		
		throw new TempPasswordNotFoundException(ExceptionConfig.TEMP_PASSWORD_NOT_FOUND_ERROR_CODE, ExceptionConfig.TEMP_PASSWORD_NOT_FOUND_EXCEPTION);
	}
		
	public void delete(String emailId) throws TempPasswordDeletionException {
		try {
			TempPassword tempPassword = findByEmailId(emailId);
			repository.delete(tempPassword);
		} catch (TempPasswordNotFoundException exception) {
			logger.error("", exception);
			throw new TempPasswordDeletionException(ExceptionConfig.TEMP_PASSWORD_DELETION_ERROR_CODE, ExceptionConfig.TEMP_PASSWORD_DELETION_EXCEPTION);
		}
		
	}
	
	public void delete(TempPassword tempPassword) throws TempPasswordDeletionException {
		repository.delete(tempPassword);
	}
}
