package com.humanize.server.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.authentication.dao.InvitationCodeRepository;
import com.humanize.server.authentication.data.InvitationCode;
import com.humanize.server.authentication.data.VerificationCode;
import com.humanize.server.authentication.exception.InvitationCodeCreationException;
import com.humanize.server.authentication.exception.InvitationCodeNotFoundException;
import com.humanize.server.authentication.exception.InvitationCodeUpdationException;
import com.humanize.server.common.ExceptionConfig;

@Service
public class InvitationCodeRepositoryService {
	
	@Autowired
	private InvitationCodeRepository repository;
	
	public InvitationCode create(InvitationCode invitationCode) {
		invitationCode = repository.save(invitationCode);
		
		if (invitationCode != null) {
			return invitationCode;
		}
		
		throw new InvitationCodeCreationException(ExceptionConfig.INVITATION_CODE_CREATION_ERROR_CODE, ExceptionConfig.INVITATION_CODE_CREATION_EXCEPTION);
	}
	
	public InvitationCode update(InvitationCode invitationCode) {
		invitationCode = repository.save(invitationCode);
		
		if (invitationCode != null) {
			return invitationCode;
		}
		
		throw new InvitationCodeUpdationException(ExceptionConfig.INVITATION_CODE_UPDATION_ERROR_CODE, ExceptionConfig.INVITATION_CODE_UPDATION_EXCEPTION);
	}
	
	public InvitationCode findByEmailId(String emailId) {
		InvitationCode invitationCode = repository.findByEmailId(emailId);
		
		if (invitationCode != null) {
			return invitationCode;
		}
		
		throw new InvitationCodeNotFoundException(ExceptionConfig.INVITATION_CODE_NOT_FOUND_ERROR_CODE, ExceptionConfig.INVITATION_CODE_NOT_FOUND_EXCEPTION);
	}
		
	public void deleteByEmailId(String emailId) {
		InvitationCode invitationCode = findByEmailId(emailId);
		repository.delete(invitationCode);
	}
	
	public void delete(InvitationCode invitationCode) {
		repository.delete(invitationCode);
	}
}
