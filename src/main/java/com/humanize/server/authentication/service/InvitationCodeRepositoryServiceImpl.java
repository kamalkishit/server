package com.humanize.server.authentication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.authentication.dao.InvitationCodeRepository;
import com.humanize.server.authentication.data.InvitationCode;
import com.humanize.server.authentication.exception.InvitationCodeCreationException;
import com.humanize.server.authentication.exception.InvitationCodeDeletionException;
import com.humanize.server.authentication.exception.InvitationCodeNotFoundException;
import com.humanize.server.authentication.exception.InvitationCodeUpdationException;
import com.humanize.server.common.ExceptionConfig;

@Service
public class InvitationCodeRepositoryServiceImpl implements InvitationCodeRepositoryService {

	@Autowired
	private InvitationCodeRepository repository;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public InvitationCode create(InvitationCode invitationCode) throws InvitationCodeCreationException {
		invitationCode = repository.save(invitationCode);
		
		if (invitationCode != null) {
			return invitationCode;
		}
		
		throw new InvitationCodeCreationException(ExceptionConfig.INVITATION_CODE_CREATION_ERROR_CODE, ExceptionConfig.INVITATION_CODE_CREATION_EXCEPTION);
	}
	
	public InvitationCode createOrUpdate(InvitationCode invitationCode) throws InvitationCodeCreationException, InvitationCodeUpdationException {
		try {
			InvitationCode tempInvitationCode = findByEmailId(invitationCode.getEmailId());
			
			tempInvitationCode.setInvitationCode(invitationCode.getInvitationCode());
			
			tempInvitationCode = repository.save(tempInvitationCode);
			
			if (tempInvitationCode == null) {
				throw new InvitationCodeUpdationException(ExceptionConfig.INVITATION_CODE_UPDATION_ERROR_CODE, ExceptionConfig.INVITATION_CODE_UPDATION_EXCEPTION);
			}
			
			return tempInvitationCode;
		} catch (InvitationCodeNotFoundException exception) {
			invitationCode = repository.save(invitationCode);
			
			if (invitationCode == null) {
				throw new InvitationCodeCreationException(ExceptionConfig.INVITATION_CODE_CREATION_ERROR_CODE, ExceptionConfig.INVITATION_CODE_CREATION_EXCEPTION);
			}
			
			return invitationCode;
		}
	}
	
	public InvitationCode update(InvitationCode invitationCode) throws InvitationCodeUpdationException {
		invitationCode = repository.save(invitationCode);
		
		if (invitationCode != null) {
			return invitationCode;
		}
		
		throw new InvitationCodeUpdationException(ExceptionConfig.INVITATION_CODE_UPDATION_ERROR_CODE, ExceptionConfig.INVITATION_CODE_UPDATION_EXCEPTION);
	}
	
	public InvitationCode findByEmailId(String emailId) throws InvitationCodeNotFoundException {
		InvitationCode invitationCode = repository.findByEmailId(emailId);
		
		if (invitationCode != null) {
			return invitationCode;
		}
		
		throw new InvitationCodeNotFoundException(ExceptionConfig.INVITATION_CODE_NOT_FOUND_ERROR_CODE, ExceptionConfig.INVITATION_CODE_NOT_FOUND_EXCEPTION);
	}
		
	public void delete(String emailId) throws InvitationCodeDeletionException {
		try {
			InvitationCode invitationCode = findByEmailId(emailId);
			repository.delete(invitationCode);
		} catch (InvitationCodeNotFoundException exception) {
			logger.error("", exception);
			throw new InvitationCodeDeletionException(ExceptionConfig.INVITATION_CODE_DELETION_ERROR_CODE, ExceptionConfig.INVITATION_CODE_DELETION_EXCEPTION);
		}
		
	}
	
	public void delete(InvitationCode invitationCode) throws InvitationCodeDeletionException {
		repository.delete(invitationCode);
	}
}
