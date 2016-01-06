package com.humanize.server.authentication.service;

import com.humanize.server.authentication.data.InvitationCode;
import com.humanize.server.authentication.exception.InvitationCodeCreationException;
import com.humanize.server.authentication.exception.InvitationCodeDeletionException;
import com.humanize.server.authentication.exception.InvitationCodeNotFoundException;
import com.humanize.server.authentication.exception.InvitationCodeUpdationException;

public interface InvitationCodeRepositoryService {
	
	InvitationCode create(InvitationCode invitationCode) throws InvitationCodeCreationException;
	InvitationCode createOrUpdate(InvitationCode invitationCode) throws InvitationCodeCreationException, InvitationCodeUpdationException;
	InvitationCode update(InvitationCode invitationCode) throws InvitationCodeUpdationException;
	InvitationCode findByEmailId(String emailId) throws InvitationCodeNotFoundException;
	void delete(String emailId) throws InvitationCodeDeletionException;
	void delete(InvitationCode invitationCode) throws InvitationCodeDeletionException;
}
