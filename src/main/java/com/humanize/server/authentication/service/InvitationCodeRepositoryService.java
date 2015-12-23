package com.humanize.server.authentication.service;

import com.humanize.server.authentication.data.InvitationCode;
import com.humanize.server.authentication.exception.InvitationCodeCreationException;
import com.humanize.server.authentication.exception.InvitationCodeDeletionException;
import com.humanize.server.authentication.exception.InvitationCodeNotFoundException;
import com.humanize.server.authentication.exception.InvitationCodeUpdationException;

public interface InvitationCodeRepositoryService {
	
	public InvitationCode create(InvitationCode invitationCode) throws InvitationCodeCreationException;
	public InvitationCode createOrUpdate(InvitationCode invitationCode) throws InvitationCodeCreationException, InvitationCodeUpdationException;
	public InvitationCode update(InvitationCode invitationCode) throws InvitationCodeUpdationException;
	public InvitationCode findByEmailId(String emailId) throws InvitationCodeNotFoundException;
	public void delete(String emailId) throws InvitationCodeDeletionException;
	public void delete(InvitationCode invitationCode);
}
