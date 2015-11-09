package com.humanize.server.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.authentication.dao.InvitationCodeRepository;
import com.humanize.server.authentication.data.InvitationCode;
import com.humanize.server.authentication.exception.InvitationCodeCreationException;
import com.humanize.server.authentication.exception.InvitationCodeNotFoundException;
import com.humanize.server.authentication.exception.InvitationCodeUpdationException;
import com.humanize.server.common.ExceptionConfig;

@Service
public class InvitationCodeRepositoryService {
	
	@Autowired
	private InvitationCodeRepository invitationCodeRepository;
	
	public InvitationCode findByEmailId(String emailId) {
		InvitationCode invitationCode = invitationCodeRepository.findByEmailId(emailId);
		
		if (invitationCode != null) {
			return invitationCode;
		}
		
		throw new InvitationCodeNotFoundException(ExceptionConfig.INVITATION_CODE_NOT_FOUND_ERROR_CODE, ExceptionConfig.INVITATION_CODE_NOT_FOUND_EXCEPTION);
	}
	
	public InvitationCode create(InvitationCode invitationCode) {
		invitationCode = invitationCodeRepository.save(invitationCode);
		
		if (invitationCode != null) {
			return invitationCode;
		}
		
		throw new InvitationCodeCreationException(ExceptionConfig.INVITATION_CODE_CREATION_ERROR_CODE, ExceptionConfig.INVITATION_CODE_CREATION_EXCEPTION);
	}
	
	public InvitationCode update(InvitationCode invitationCode) {
		invitationCode = invitationCodeRepository.save(invitationCode);
		
		if (invitationCode != null) {
			return invitationCode;
		}
		
		throw new InvitationCodeUpdationException(ExceptionConfig.INVITATION_CODE_UPDATION_ERROR_CODE, ExceptionConfig.INVITATION_CODE_UPDATION_EXCEPTION);
	}
}
